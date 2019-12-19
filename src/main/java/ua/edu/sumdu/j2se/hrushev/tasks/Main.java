package ua.edu.sumdu.j2se.hrushev.tasks;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        LocalDateTime TODAY = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime YESTERDAY = TODAY.minusDays(1);
        LocalDateTime TOMORROW = TODAY.plusDays(1);
        LocalDateTime TODAY_1H = TODAY.plusHours(1);
        LocalDateTime TODAY_2H = TODAY.plusHours(2);
        LocalDateTime TODAY_3H = TODAY.plusHours(3);
        LocalDateTime TODAY_4H = TODAY.plusHours(4);
        LocalDateTime ALMOST_TODAY = TODAY.minusSeconds(1);

        Task daily = new Task("Daily", YESTERDAY, TOMORROW, 3600*24);
        Task hourly = new Task("Hourly", TODAY, TOMORROW, 3600);
        Task every3h = new Task("Every 3 hours", TODAY_1H, TOMORROW, 3*3600);
        Task task = new Task("some", TODAY, TOMORROW, 3600);

        daily.setActive(true);
        hourly.setActive(true);
        every3h.setActive(true);
        task.setActive(true);

        System.out.println(task.nextTimeAfter(TOMORROW));
    }
}
