package ua.edu.sumdu.j2se.hrushev.tasks.view;

import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class NotificationView implements Viewable {
    private Logger logger = Logger.getLogger(String.valueOf(Main.class));

    @Override
    public int view(AbstractTaskList list) {
        for (Task task: list) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime halfHourLater = now.plusMinutes(30);
            LocalDateTime secondsLater = now.plusSeconds(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            if (task != null && task.isActive() && task.nextTimeAfter(now) != null && task.nextTimeAfter(now).isBefore(halfHourLater) && !(task.nextTimeAfter(now).isBefore(secondsLater))) {
                System.out.println("\n---NOTIFICATION---");
                System.out.println("Task " + task.getTitle() + " is supposed to be done at " + task.nextTimeAfter(now).format(formatter));
                System.out.println("---NOTIFICATION---\n");
                System.out.println("Continue your input here:");

                logger.info("User got notification about this task: " + task + ".");
            }
        }

        return 0;
    }

    public void taskNotification(AbstractTaskList list) {
        for (Task task: list) {
            LocalDateTime now = LocalDateTime.now().withNano(0);
            LocalDateTime later = now.plusSeconds(2);

            if (task != null && task.isActive() && task.nextTimeAfter(now) != null && task.nextTimeAfter(now).isBefore(later)) {
                NotificationThread thread = new NotificationThread(task, now);
                thread.start();
            }
        }
    }
}
