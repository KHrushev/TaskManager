package ua.edu.sumdu.j2se.hrushev.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime from_now_10 = now.plusSeconds(10);
        LocalDateTime from_now_20 = now.plusSeconds(20);
        LocalDateTime from_now_30 = now.plusSeconds(30);
        LocalDateTime from_now_40 = now.plusSeconds(40);

        Task task1 = new Task("Task1", now);
        Task task2 = new Task("Task2", from_now_10);
        Task task3 = new Task("Task3", from_now_20);

        task1.setActive(true);
        task2.setActive(true);
        task3.setActive(true);

        ArrayList<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task2);
        list.add(task3);

        System.out.println(Tasks.calendar(list, now.minusSeconds(1), from_now_40));
    }
}
