package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.view.TasksView;

public class TasksController extends Controller {
    private final Logger logger = Logger.getLogger(Main.class);

    @Override
    public int process(AbstractTaskList list) {
        TasksView view = new TasksView();

        view.view(list);

        logger.info("Tasks Controller finished showing user task list.");

        return 0;
    }
}
