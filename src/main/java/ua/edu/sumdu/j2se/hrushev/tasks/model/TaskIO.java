package ua.edu.sumdu.j2se.hrushev.tasks.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.time.*;

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
        try(Writer writer = out) {
            AbstractTaskList list = tasks instanceof ArrayTaskList ? new ArrayTaskList() : new LinkedTaskList();
            for(Task task : tasks) {
                list.add(task);
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(list, tasks instanceof ArrayTaskList ? ArrayTaskList.class : LinkedTaskList.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) {
        AbstractTaskList list;
        Type type = tasks instanceof ArrayTaskList ? ArrayTaskList.class : LinkedTaskList.class;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        list = gson.fromJson(in, type);
        for(Task task : list) {
            tasks.add(task);
        }
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
