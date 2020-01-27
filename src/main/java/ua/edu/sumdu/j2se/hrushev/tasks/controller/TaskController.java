package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.view.TaskView;
import ua.edu.sumdu.j2se.hrushev.tasks.view.Viewable;

public class TaskController extends Controller {
    private Viewable view;

    public TaskController(Viewable view) {
        this.view = view;
    }

    @Override
    public int process(AbstractTaskList list) {
        if (list.size() == 0) {
            System.out.println("List does not contain any items yet.\n");
            return -1;
        } else {
            int index = view.view(list);
            System.out.println(index);
            if (list.size() <= index || index < 0) {
                System.out.println("There is no task with given index. Try Again.");
                this.process(list);
            } else {
                if (this.view instanceof TaskView) {
                    ((TaskView) this.view).displayTask(list.getTask(index));
                }
            }

            return 0;
        }

    }
}
