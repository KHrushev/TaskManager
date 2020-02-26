package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Observer;
import ua.edu.sumdu.j2se.hrushev.tasks.view.NotificationView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationController extends Controller implements Observer {
    private AbstractTaskList list;
    private boolean changed = false;

    @Override
    public int process(AbstractTaskList list) {
        NotificationView view = new NotificationView();

        if (changed) {
            list = this.list;
        }

        AbstractTaskList finalList = list;

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();

        exec.scheduleAtFixedRate(() -> view.taskNotification(finalList), 0, 1, TimeUnit.SECONDS);

        exec.scheduleAtFixedRate(() -> view.view(finalList), 0, 5, TimeUnit.MINUTES);

        return 0;
    }

    @Override
    public void update(AbstractTaskList list) {
        this.list = list;
        changed = true;
    }
}
