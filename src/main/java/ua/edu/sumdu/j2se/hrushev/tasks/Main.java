package ua.edu.sumdu.j2se.hrushev.tasks;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime today = now.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime tomorrow = today.plusDays(1);
        LocalDateTime today_1h = today.plusHours(1);

        Task task1 = new Task("some", today, tomorrow, 3600);

        task1.setActive(true);

        System.out.println(task1.nextTimeAfter(today));
        System.out.println(task1.nextTimeAfter(today_1h));
    }
}
