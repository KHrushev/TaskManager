package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Observer;
import ua.edu.sumdu.j2se.hrushev.tasks.view.*;

import java.util.HashSet;
import java.util.Set;

public class MainController extends Controller{
    private final Logger logger = LogManager.getLogger(Main.class);

    private Viewable view;
    private Set<Controller> controllers = new HashSet<>();
    private boolean startup = true;

    public MainController(Viewable view) {
        this.view = view;
        controllers.add(new AddController());
        controllers.add(new RemoveController());
        controllers.add(new EditingController());
        controllers.add(new CalendarController());
        controllers.add(new TaskController());
        controllers.add(new TasksController());
        controllers.add(new SaveController());
        controllers.add(new NotificationController());
        logger.info("Controllers initialized.");
    }

    @Override
    public int process(AbstractTaskList list) {
        if (startup) {
            for (Controller c: controllers) {
                if (c instanceof SaveController) {
                    ((SaveController) c).load(list);
                    logger.info("Save file loaded.");
                }
            }
            for (Controller c: controllers) {
                if (c instanceof NotificationController) {
                    list.addObserver((Observer) c);
                    c.process(list);
                    logger.info("Notification controller launched.");
                } else if (c instanceof CalendarController) {
                    list.addObserver((Observer) c);
                }
            }
        }

        startup = false;

        int choice = view.view(list);

        switch (choice) {
            case VIEW_TASK_ACTION:
                for (Controller c : controllers) {
                    if (c instanceof TaskController) {
                        c.process(list);
                    }
                }
                this.process(list);
                break;
            case VIEW_TASKS_ACTION:
                for (Controller c : controllers) {
                    if (c instanceof TasksController) c.process(list);
                }
                this.process(list);
                break;
            case ADD_TASK_ACTION:
                for (Controller c : controllers) {
                    if (c instanceof AddController) c.process(list);
                }
                this.process(list);
                break;
            case REMOVE_TASK_ACTION:
                for (Controller c : controllers) {
                    if (c instanceof RemoveController) c.process(list);
                }
                this.process(list);
                break;
            case EDIT_ACTION:
                for (Controller c : controllers) {
                    if (c instanceof EditingController) c.process(list);
                }
                this.process(list);
                break;
            case CALENDAR_ACTION:
                for (Controller c : controllers) {
                    if (c instanceof CalendarController) c.process(list);
                }
                this.process(list);
                break;
            case EXIT_ACTION:
                for (Controller c : controllers) {
                    if (c instanceof SaveController) {
                        c.process(list);
                        logger.info("Saving the list, then exiting the app.");
                    }
                }
                System.exit(0);
            default:
                logger.info("User entered incorrect action number, trying again.");
                this.process(list);
                break;
        }

        logger.info("Finished app execution.");

        return 0;
    }
}
