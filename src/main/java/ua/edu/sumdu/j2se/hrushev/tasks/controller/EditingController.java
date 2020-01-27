package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.view.EditingView;
import ua.edu.sumdu.j2se.hrushev.tasks.view.TaskView;
import ua.edu.sumdu.j2se.hrushev.tasks.view.Viewable;

import java.time.LocalDateTime;

public class EditingController extends Controller {
    public final int TASK_NAME = 0;
    public final int TASK_START_TIME = 1;
    public final int TASK_END_TIME = 2;
    public final int TASK_INTERVAL = 3;
    public final int TASK_CONVERT = 4;
    public final int TASK_ACTIVITY = 5;

    private Viewable view;

    public EditingController(Viewable view) {
        this.view = view;
    }

    @Override
    public int process(AbstractTaskList list) {
        int index = this.view.view(list);

        if (index < 0 || index > list.size()-1) {
            System.out.println("There is no Task with entered index. Try Again.\n");
            this.process(list);
        } else {
            System.out.println("You chose this task:");
            TaskView taskDisplayer = new TaskView();
            taskDisplayer.displayTask(list.getTask(index));

            if (this.view instanceof EditingView) {
                int propertyId = ((EditingView) this.view).propertySelection(list);
                if (propertyId == -1) return 0;
                while ( (propertyId == 2 || propertyId == 3) && !(list.getTask(index).isRepeated()) ) {
                    System.out.println("Chosen Task is not repeatable and it has no ending time or interval.\n");
                    propertyId = ((EditingView) this.view).propertySelection(list);
                }

                switch (propertyId) {
                    case TASK_NAME:
                        list.getTask(index).setTitle(((EditingView) this.view).getNewName());
                        break;
                    case TASK_START_TIME:
                        LocalDateTime end = list.getTask(index).getEndTime();
                        int interval = list.getTask(index).getRepeatInterval();
                        list.getTask(index).setTime(((EditingView) this.view).getNewStart(), end, interval);
                        break;
                    case TASK_END_TIME:
                        LocalDateTime start = list.getTask(index).getStartTime();
                        interval = list.getTask(index).getRepeatInterval();
                        list.getTask(index).setTime(start, ((EditingView) this.view).getNewEnd(), interval);
                        break;
                    case TASK_INTERVAL:
                        start = list.getTask(index).getStartTime();
                        end = list.getTask(index).getEndTime();
                        list.getTask(index).setTime(start, end, ((EditingView) this.view).getNewInterval());
                        break;
                    case TASK_CONVERT:
                        if (list.getTask(index).isRepeated()) {
                            list.getTask(index).setTime(list.getTask(index).getStartTime());
                        } else {
                            list.getTask(index).setTime(((EditingView) this.view).getNewStart(), ((EditingView) this.view).getNewEnd(), ((EditingView) this.view).getNewInterval());
                        }
                        break;
                    case TASK_ACTIVITY:
                        list.getTask(index).setActive( !(list.getTask(index).isActive()) );
                        System.out.println("\nActivity status toggled.\n");
                }
            }
        }
        return 0;
    }
}
