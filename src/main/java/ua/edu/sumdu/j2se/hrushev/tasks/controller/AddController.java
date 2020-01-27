package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.view.AddView;
import ua.edu.sumdu.j2se.hrushev.tasks.view.Viewable;

public class AddController extends Controller {
    private Viewable view;

    public AddController(Viewable view) {
        this.view = view;
    }

    @Override
    public int process(AbstractTaskList list) {
        int choice = this.view.view(list);
        if (this.view instanceof AddView) {
            if (choice == 0) {
                try {
                    list.add(((AddView) this.view).singleTaskView());
                    System.out.println("\nTask added successfully.\n");
                } catch (IllegalArgumentException iae) {
                    System.out.println("You've entered incorrect data for task, try again.\n");
                    this.process(list);
                }
            } else if (choice == 1) {
                try {
                    list.add( ((AddView) this.view).repeatableTaskView() );
                    System.out.println("\nTask added successfully.\n");
                } catch (IllegalArgumentException iae) {
                    System.out.println("You've entered incorrect data for task, try again.\n");
                    this.process(list);
                }
            } else if (choice == -1) {
                System.out.println("You've answered incorrectly, please, try again.\n");
                this.process(list);
            }
        }
        return 0;
    }
}
