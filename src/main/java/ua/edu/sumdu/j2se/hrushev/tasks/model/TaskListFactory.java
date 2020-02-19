package ua.edu.sumdu.j2se.hrushev.tasks.model;

import static ua.edu.sumdu.j2se.hrushev.tasks.model.ListTypes.types.*;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type) {
        if (type.equals(ARRAY)) {
            return new ArrayTaskList();
        } else {
            return new LinkedTaskList();
        }
    }
}
