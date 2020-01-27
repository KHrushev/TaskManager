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
//        tasks.add(new Task("asd", now, now.plusHours(5), 2000));
//        tasks.add(new Task("asdf", now));
//        tasks.add(new Task("asdds", now));
//
//        tasks.getTask(0).setActive(true);
//        tasks.getTask(1).setActive(true);
//        tasks.getTask(2).setActive(true);

        Viewable view = new MainMenuView();
        Controller controller = new MainController(view, tasks);
        controller.process(tasks);
    }
}
