package ua.edu.sumdu.j2se.hrushev.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.io.*;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.time.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaskIO {
    public static void write(AbstractTaskList tasks, OutputStream out) {
        try (DataOutputStream oos = new DataOutputStream(out)) {
            oos.write(tasks.size());
            for(Task task : tasks) {
                oos.write(task.getTitle().length());
                oos.writeUTF(task.getTitle());
                oos.writeBoolean(task.isActive());
                oos.write(task.getRepeatInterval());
                if (task.isRepeated()) {
                    Instant startInstant = task.getStartTime().atZone(ZoneId.systemDefault()).toInstant();
                    long startMillis = startInstant.toEpochMilli();
                    oos.writeLong(startMillis);

                    Instant endInstant = task.getEndTime().atZone(ZoneId.systemDefault()).toInstant();
                    long endMillis = endInstant.toEpochMilli();
                    oos.writeLong(endMillis);
                } else {
                    Instant timeInstant = task.getTime().atZone(ZoneId.systemDefault()).toInstant();
                    long timeMillis = timeInstant.toEpochMilli();
                    oos.writeLong(timeMillis);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) {
        try (DataInputStream dis = new DataInputStream(in)) {
            int size = dis.read();
            Task task;
            String title;
            boolean active;
            int repeatInterval;
            LocalDateTime start, end, time;
            for (int i = 0; i < size; i++) {
                dis.read();
                title = dis.readUTF();
                active = dis.readBoolean();
                repeatInterval = dis.read();
                if(repeatInterval > 0 ) {
                    start = LocalDateTime.ofEpochSecond(dis.readLong(), 0, ZoneOffset.UTC);
                    end = LocalDateTime.ofEpochSecond(dis.readLong(), 0, ZoneOffset.UTC);
                    task = new Task(title, start, end, repeatInterval);
                } else {
                    time = LocalDateTime.ofEpochSecond(dis.readLong(), 0, ZoneOffset.UTC);
                    task = new Task(title, time);
                }
                task.setActive(active);
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) {
        try {
            TaskIO.write(tasks, new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) {
        try {
            TaskIO.read(tasks, new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            out.write(gson.toJson(tasks));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) {
        Gson gson = new Gson();
        AbstractTaskList tempList = tasks instanceof ArrayTaskList ? gson.fromJson(in, ArrayTaskList.class) : gson.fromJson(in, LinkedTaskList.class);
        tempList.getStream().filter(Objects::nonNull).forEach(tasks::add);
        System.out.println(tempList);
        System.out.println(tasks);
//        tempList.forEach(tasks::add);
//        for (Task task : tempList) {
//            if (task != null) tasks.add(task);
//        }
    }

    public static void writeText(AbstractTaskList tasks, File file) {
        try (FileWriter fwr = new FileWriter(file)){
            write(tasks, fwr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readText(AbstractTaskList tasks, File file) {
        try (FileReader frd = new FileReader(file)){
            read(tasks, frd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
