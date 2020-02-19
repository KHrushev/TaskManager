package ua.edu.sumdu.j2se.hrushev.tasks.view;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;

public class TasksView implements Viewable {

    @Override
    public int view(AbstractTaskList list) {
        System.out.println("List length: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ": " + list.getTask(i));
        }
        System.out.println("");

        return 0;
    }
}
