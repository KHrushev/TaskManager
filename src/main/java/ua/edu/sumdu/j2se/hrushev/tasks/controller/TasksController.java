package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.view.Viewable;

public class TasksController extends Controller {
    private final Logger logger = Logger.getLogger(Main.class);

    private Viewable view;

    public TasksController(Viewable view) {
        this.view = view;
    }

    @Override
    public int process(AbstractTaskList list) {
        this.view.view(list);

        logger.info("Tasks Controller finished showing user task list.");

        return 0;
    }
}
