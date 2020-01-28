package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Observer;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationController extends Controller implements Observer {
    private AbstractTaskList list;
    private boolean changed = false;

    @Override
    public int process(AbstractTaskList list) {
        if (changed) {
            list = this.list;
        }

        AbstractTaskList finalList = list;

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(() -> {
            for (Task task: finalList) {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime halfHourLater = now.plusMinutes(30);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                if (task != null && task.isActive() && task.nextTimeAfter(now).isBefore(halfHourLater)) {
                    System.out.println("\n---NOTIFICATION---");
                    System.out.println("Task " + task.getTitle() + " is supposed to be done at " + task.nextTimeAfter(now).format(formatter));
                    System.out.println("---NOTIFICATION---\n");
                    System.out.println("Continue your input here:");
                }
            }
        }, 0, 5, TimeUnit.MINUTES);

        return 0;
    }

    @Override
    public void update(AbstractTaskList list) {
        this.list = list;
        changed = true;
    }
}
