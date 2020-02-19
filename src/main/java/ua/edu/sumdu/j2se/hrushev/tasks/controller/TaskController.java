package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.view.Viewable;

import java.util.logging.Logger;

public class TaskController extends Controller {
    private final Logger logger = Logger.getLogger(String.valueOf(Main.class));
    private Viewable view;

    public TaskController(Viewable view) {
        this.view = view;
    }

    @Override
    public int process(AbstractTaskList list) {
        view.view(list);

        logger.info("Showed task chosen by the use via TaskController.");

        return 0;
    }
}
