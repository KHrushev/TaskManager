package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Observer;
import ua.edu.sumdu.j2se.hrushev.tasks.view.*;

import java.util.HashSet;
import java.util.Set;

public class MainController extends Controller{
    private Viewable view;
    private Set<Controller> controllers = new HashSet<>();
    private boolean startup = true;

    public MainController(Viewable view) {
        this.view = view;
        controllers.add(new AddController(new AddView()));
        controllers.add(new RemoveController(new RemoveView()));
        controllers.add(new EditingController(new EditingView()));
        controllers.add(new CalendarController(new CalendarView()));
        controllers.add(new TaskController(new TaskView()));
        controllers.add(new TasksController(new TasksView()));
        controllers.add(new SaveController());
        controllers.add(new NotificationController());
    }

    @Override
    public int process(AbstractTaskList list) {
        if (startup) {
            for (Controller c: controllers) {
                if (c instanceof SaveController) {
                    ((SaveController) c).load(list);
                }
            }
            for (Controller c: controllers) {
                if (c instanceof NotificationController) {
                    list.addObserver((Observer) c);
                    c.process(list);
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
                System.out.println("Exiting App. . .");
                for (Controller c : controllers) {
                    if (c instanceof SaveController) c.process(list);
                }
                System.exit(0);
            default:
                System.out.println("Looks like you've entered incorrect character. Try again.");
                this.process(list);
                break;
        }

        return 0;
    }
}
