package ua.edu.sumdu.j2se.hrushev.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws IOException {
        LocalDateTime TODAY = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime YESTERDAY = TODAY.minusDays(1);
        LocalDateTime TOMORROW = TODAY.plusDays(1);

        Task task1 = new Task("some1", YESTERDAY, TOMORROW, 3600*24);
        Task task2 = new Task("some2", TODAY, TOMORROW, 3600);
        Task task3 = new Task("some3", TODAY, TOMORROW, 3*3600);
        Task task4 = new Task("some4", TODAY, TOMORROW, 3600);

        task1.setActive(true);
        task2.setActive(true);
        task3.setActive(true);
        task4.setActive(true);

        AbstractTaskList tasks = new ArrayTaskList();
        AbstractTaskList empty;
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("test.json")){
            gson.toJson(tasks, writer);
        }

        try (FileReader reader = new FileReader("test.json")){
            empty = gson.fromJson(reader, ArrayTaskList.class);
            System.out.println(empty);
        }
    }
}
