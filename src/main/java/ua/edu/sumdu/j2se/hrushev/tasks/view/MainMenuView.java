package ua.edu.sumdu.j2se.hrushev.tasks.view;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenuView implements Viewable {
    private final Logger logger = LogManager.getLogger(Main.class);

    @Override
    public int view(AbstractTaskList list) {

        int choice = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Main Menu:");
        System.out.println("1.View Task by Index.");
        System.out.println("2.View all Tasks.");
        System.out.println("3.Add Task.");
        System.out.println("4.Remove Task/Tasks.");
        System.out.println("5.Edit a Task.");
        System.out.println("6.Show Calendar.");
        System.out.println("7.Exit App.");

        try {
            System.out.println("Your Input:");
            String stringChoice = reader.readLine();
            choice = Integer.parseInt(stringChoice);
            if (choice < 0 || choice > 7) throw new IOException();
        } catch (IOException | NumberFormatException e) {
            System.out.println("You've entered incorrect index, try again.\n");
            return this.view(list);
        }

        logger.info("Got user choice: " + choice + ".");

        return choice;
    }
}
