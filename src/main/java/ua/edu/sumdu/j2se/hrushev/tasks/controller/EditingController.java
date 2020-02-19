package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.view.EditingView;
import ua.edu.sumdu.j2se.hrushev.tasks.view.TaskView;

import java.time.LocalDateTime;

public class EditingController extends Controller {
    private final Logger logger = LogManager.getLogger(Main.class);

    public final int TASK_NAME = 0;
    public final int TASK_START_TIME = 1;
    public final int TASK_END_TIME = 2;
    public final int TASK_INTERVAL = 3;
    public final int TASK_CONVERT = 4;
    public final int TASK_ACTIVITY = 5;

    @Override
    public int process(AbstractTaskList list) {
        EditingView view = new EditingView();

        int index = view.view(list);

        TaskView taskDisplayer = new TaskView();
        taskDisplayer.displayTask(list.getTask(index));

        int propertyId = view.propertySelection(list, index);

        if (propertyId == -1) {
            return 0;
        }

        switch (propertyId) {
            case TASK_NAME:
                list.getTask(index).setTitle(view.getNewName());
                break;
            case TASK_START_TIME:
                LocalDateTime start = view.getNewStart();
                LocalDateTime end = list.getTask(index).getEndTime();
                int interval = list.getTask(index).getRepeatInterval();

                if (list.getTask(index).isRepeated()) {
                    list.getTask(index).setTime(start, end, interval);
                } else {
                    list.getTask(index).setTime(start);
                    list.getTask(index).setTime(null, null, 0);
                }
                break;
            case TASK_END_TIME:
                start = list.getTask(index).getStartTime();
                interval = list.getTask(index).getRepeatInterval();
                list.getTask(index).setTime(start, view.getNewEnd(), interval);
                break;
            case TASK_INTERVAL:
                start = list.getTask(index).getStartTime();
                end = list.getTask(index).getEndTime();
                list.getTask(index).setTime(start, end, view.getNewInterval());
                break;
            case TASK_CONVERT:
                if (list.getTask(index).isRepeated()) {
                    list.getTask(index).setTime(list.getTask(index).getStartTime());
                } else {
                    list.getTask(index).setTime(view.getNewStart(), view.getNewEnd(), view.getNewInterval());
                }
                break;
            case TASK_ACTIVITY:
                list.getTask(index).setActive( !(list.getTask(index).isActive()) );
                break;
        }
        list.notifyObservers();

        logger.info("Edited task via Editing Controller. Task index - " + index + ".");

        SaveController controller = new SaveController();
        controller.process(list);

        logger.info("Saved edited list to a save file.");

        return 0;
    }
}
