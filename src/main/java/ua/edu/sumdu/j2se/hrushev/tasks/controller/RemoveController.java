package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Task;
import ua.edu.sumdu.j2se.hrushev.tasks.view.RemoveView;
import ua.edu.sumdu.j2se.hrushev.tasks.view.Viewable;

public class RemoveController extends Controller {
    private Viewable view;

    public RemoveController(Viewable view) {
        this.view = view;
    }

    @Override
    public int process(AbstractTaskList list) {
        int choice = this.view.view(list);

        if (choice == 0) {
            if (this.view instanceof RemoveView) {
                int index = ((RemoveView) this.view).removeSingle(list);
                Task taskToDelete = list.getTask(index);
                list.remove(taskToDelete);

                System.out.println("\nTask deleted successfully.\n");
            }
        } else if (choice == 1) {
            list.clear();
            System.out.println("\nList cleared.\n");
        }
        return 0;
    }
}
