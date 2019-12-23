package ua.edu.sumdu.j2se.hrushev.tasks;

import java.io.*;
import java.time.*;

public class TaskIO {
    public static void write(AbstractTaskList tasks, OutputStream out) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(out);
            DataOutputStream dataInputStream = new DataOutputStream(oos);

            dataInputStream.writeInt(tasks.size());

            for (Task task : tasks) {
                dataInputStream.writeInt(task.getTitle().length());
                dataInputStream.writeChars(task.getTitle());
                dataInputStream.writeBoolean(task.isActive());
                dataInputStream.writeInt(task.getRepeatInterval());
                if (task.isRepeated()) {
                    Instant startInstant = task.getStartTime().atZone(ZoneId.systemDefault()).toInstant();
                    long startMillis = startInstant.toEpochMilli();
                    dataInputStream.writeLong(startMillis);

                    Instant endInstant = task.getEndTime().atZone(ZoneId.systemDefault()).toInstant();
                    long endMillis = endInstant.toEpochMilli();
                    dataInputStream.writeLong(endMillis);
                } else {
                    Instant timeInstant = task.getTime().atZone(ZoneId.systemDefault()).toInstant();
                    long timeMillis = timeInstant.toEpochMilli();
                    dataInputStream.writeLong(timeMillis);
                }
            }

//            oos.flush();
//            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) {
        try {
            DataInputStream inputStream = new DataInputStream(in);
            int size = inputStream.read();

            for (int i = 0; i < size; i++) {
                int titleLength = inputStream.read();
                String title = inputStream.readUTF();
                boolean isActive = inputStream.readBoolean();
                int interval = inputStream.read();
                if (interval > 0) {
                    long startMillis = inputStream.read();
                    LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochMilli(startMillis), ZoneId.systemDefault());
                    long endMillis = inputStream.read();
                    LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochMilli(endMillis), ZoneId.systemDefault());

                    Task newTask = new Task(title, start, end, interval);
                    newTask.setActive(isActive);
                    tasks.add(newTask);
                } else {
                    long timeMillis = inputStream.read();
                    LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), ZoneId.systemDefault());

                    Task newTask = new Task(title, time);
                    newTask.setActive(isActive);
                    tasks.add(newTask);
                }
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            TaskIO.write(tasks, fos);

//            fos.flush();
//            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            TaskIO.read(tasks, fis);

            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
