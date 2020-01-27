package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.view.Viewable;

public abstract class Controller {
    public final int VIEW_TASK_ACTION = 1;
    public final int VIEW_TASKS_ACTION = 2;
    public final int ADD_TASK_ACTION = 3;
    public final int REMOVE_TASK_ACTION = 4;
    public final int EDIT_ACTION = 5;
    public final int CALENDAR_ACTION = 6;
    public final int EXIT_ACTION = 7;

    private Viewable view;

    public abstract int process(AbstractTaskList list);
}
