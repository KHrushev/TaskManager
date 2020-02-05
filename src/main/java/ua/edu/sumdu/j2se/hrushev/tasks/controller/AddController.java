package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Task;
import ua.edu.sumdu.j2se.hrushev.tasks.view.AddView;
import ua.edu.sumdu.j2se.hrushev.tasks.view.Viewable;

public class AddController extends Controller {
    private final Logger logger = Logger.getLogger(Main.class);
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
                    Task task = ((AddView) this.view).singleTaskView();
                    list.add(task);
                    ((AddView) this.view).confirm();
                } catch (IllegalArgumentException iae) {
                    logger.error("Got IllegalArgumentException trying to add single-use task.");
                    ((AddView) this.view).error();
                    this.process(list);
                }
            } else if (choice == 1) {
                try {
                    list.add( ((AddView) this.view).repeatableTaskView() );
                    ((AddView) this.view).confirm();
                } catch (IllegalArgumentException iae) {
                    logger.error("Got IllegalArgumentException trying to add repeatable task.");
                    ((AddView) this.view).error();
                    this.process(list);
                }
            } else if (choice == -1) {
                ((AddView) this.view).error();
                this.process(list);
            }
        }

        list.notifyObservers();

        logger.info("Added new task via Add Controller.");

        return 0;
    }
}
