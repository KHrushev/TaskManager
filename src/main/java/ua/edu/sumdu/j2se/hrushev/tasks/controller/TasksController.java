package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.view.Viewable;

public class TasksController extends Controller {
    private Viewable view;

    public TasksController(Viewable view) {
        this.view = view;
    }

    @Override
    public int process(AbstractTaskList list) {
        this.view.view(list);
        return 0;
    }
}
