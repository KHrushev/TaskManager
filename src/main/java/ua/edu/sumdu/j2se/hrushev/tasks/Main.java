package ua.edu.sumdu.j2se.hrushev.tasks;

import ua.edu.sumdu.j2se.hrushev.tasks.controller.*;
import ua.edu.sumdu.j2se.hrushev.tasks.model.*;
import ua.edu.sumdu.j2se.hrushev.tasks.view.*;

public class Main {
    public static void main(String[] args) {
        AbstractTaskList tasks = new ArrayTaskList();

        Viewable view = new MainMenuView();
        Controller controller = new MainController(view);
        controller.process(tasks);
    }
}
