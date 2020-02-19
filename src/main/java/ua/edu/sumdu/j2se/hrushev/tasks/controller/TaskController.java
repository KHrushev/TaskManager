package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.view.TaskView;

import java.util.logging.Logger;

public class TaskController extends Controller {
    private final Logger logger = Logger.getLogger(String.valueOf(Main.class));

    @Override
    public int process(AbstractTaskList list) {
        TaskView view = new TaskView();

        view.view(list);

        logger.info("Showed task chosen by the use via TaskController.");

        return 0;
    }
}
