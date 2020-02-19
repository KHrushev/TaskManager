package ua.edu.sumdu.j2se.hrushev.tasks;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.edu.sumdu.j2se.hrushev.tasks.controller.Controller;
import ua.edu.sumdu.j2se.hrushev.tasks.controller.MainController;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.view.MainMenuView;
import ua.edu.sumdu.j2se.hrushev.tasks.view.Viewable;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");

        AbstractTaskList tasks = new ArrayTaskList();

        Viewable view = new MainMenuView();
        Controller controller = new MainController(view);

        logger.info("App started. Main menu to be displayed.");

        controller.process(tasks);
    }
}
