package ua.edu.sumdu.j2se.hrushev.tasks.view;

import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class NotificationThread extends Thread {
    private Logger logger = Logger.getLogger(String.valueOf(Main.class));

    private Task task;
    private LocalDateTime now;

    public NotificationThread(Task task, LocalDateTime now) {
        this.task = task;
        this.now = now;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);

            System.out.println("\n---NOTIFICATION---");
            System.out.println("Task " + task.getTitle() + " is supposed to be done now !");
            System.out.println("---NOTIFICATION---\n");
            System.out.println("Continue your input here:");

            logger.info("User got notification about this task: " + task + ".");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
