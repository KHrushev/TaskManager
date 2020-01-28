package ua.edu.sumdu.j2se.hrushev.tasks;

import ua.edu.sumdu.j2se.hrushev.tasks.controller.*;
import ua.edu.sumdu.j2se.hrushev.tasks.model.*;
import ua.edu.sumdu.j2se.hrushev.tasks.view.*;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime today = now.withHour(0).withMinute(0).withSecond(0);
        AbstractTaskList tasks = new ArrayTaskList();

        Viewable view = new MainMenuView();
        Controller controller = new MainController(view);
        controller.process(tasks);
    }
}
