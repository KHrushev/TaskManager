package ua.edu.sumdu.j2se.hrushev.tasks.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Task;
import ua.edu.sumdu.j2se.hrushev.tasks.view.RemoveView;
import ua.edu.sumdu.j2se.hrushev.tasks.view.Viewable;

public class RemoveController extends Controller {
    private final Logger logger = LogManager.getLogger(Main.class);
    private Viewable view;

    public RemoveController(Viewable view) {
        this.view = view;
    }

    @Override
    public int process(AbstractTaskList list) {
        int choice = this.view.view(list);

        if (this.view instanceof RemoveView) {
            if (choice == 0) {
                int index = ((RemoveView) this.view).removeSingle(list);
                Task taskToDelete = list.getTask(index);
                list.remove(taskToDelete);

                logger.info("Removed Task via Remove Controller.");
            } else if (choice == 1) {
                list.clear();
                logger.info("List cleared via Remove Controller.");
            }
        }

        SaveController controller = new SaveController();
        controller.process(list);

        logger.info("Saved edited list to a save file.");

        return 0;
    }
}
