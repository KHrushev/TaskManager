package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Observer;
import ua.edu.sumdu.j2se.hrushev.tasks.view.NotificationView;
import ua.edu.sumdu.j2se.hrushev.tasks.view.Viewable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationController extends Controller implements Observer {
    private AbstractTaskList list;
    private boolean changed = false;
    private Viewable view;

    public NotificationController(Viewable view) {
        this.view = view;
    }

    @Override
    public int process(AbstractTaskList list) {
        if (changed) {
            list = this.list;
        }

        AbstractTaskList finalList = list;

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();

        exec.scheduleAtFixedRate(() -> {
            if (this.view instanceof NotificationView) this.view.view(finalList);
        }, 0, 5, TimeUnit.MINUTES);


        return 0;
    }

    @Override
    public void update(AbstractTaskList list) {
        this.list = list;
        changed = true;
    }
}
