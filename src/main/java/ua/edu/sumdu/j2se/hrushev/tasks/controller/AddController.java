package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Task;
import ua.edu.sumdu.j2se.hrushev.tasks.view.AddView;

public class AddController extends Controller {
    private final Logger logger = Logger.getLogger(Main.class);

    @Override
    public int process(AbstractTaskList list) {
        AddView view = new AddView();
        int choice = view.view(list);
        if (choice == 0) {
            try {
                Task task = view.singleTaskView();
                list.add(task);
            } catch (IllegalArgumentException iae) {
                logger.error("Got IllegalArgumentException trying to add single-use task.");
                this.process(list);
            }
        } else if (choice == 1) {
            try {
                Task task = view.repeatableTaskView();
                list.add(task);
            } catch (IllegalArgumentException iae) {
                logger.error("Got IllegalArgumentException trying to add repeatable task.");
                this.process(list);
            }
        } else if (choice == -1) {
            this.process(list);
        }

        list.notifyObservers();

        logger.info("Added new task via Add Controller.");

        SaveController controller = new SaveController();
        controller.process(list);

        logger.info("Saved edited list to a save file.");

        return 0;
    }
}
