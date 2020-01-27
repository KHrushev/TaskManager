package ua.edu.sumdu.j2se.hrushev.tasks.view;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class EditingView implements Viewable, DateGetter {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int view(AbstractTaskList list) {
        System.out.println("Enter Task index to edit:");

        try {
            return Integer.parseInt(reader.readLine());
        } catch (NumberFormatException nfe) {
            System.out.println("You have to enter a number.\n");
            return this.view(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int propertySelection(AbstractTaskList list) {
        System.out.println("You can edit: task's name, starting time, activity status, ending time and interval, if the task is repeatable." +
                " You can also convert single-use task into a repeatable one.\n" +
                "Type in 'Cancel' to return to the main menu.\n");
        System.out.println("Enter property to edit:");
        try {
            String property = reader.readLine();
            switch (property.toLowerCase()) {
                case "name":
                    return 0;
                case "starting time":
                case "starting":
                case "start":
                    return 1;
                case "ending time":
                case "ending":
                case "end":
                    return 2;
                case "interval":
                    return 3;
                case "convert":
                    return 4;
                case "activity status":
                case "activity":
                    return 5;
                case "cancel":
                    break;
                default:
                    System.out.println("There is no such property. Try again.\n");
                    return this.propertySelection(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public String getNewName() {
        System.out.println("Enter new name:");
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ""; //This return statement is practically unreachable, because any string will do as a new name for a Task.
    }

    public LocalDateTime getNewStart() {
        System.out.println("Enter new starting time: (yyyy-MM-dd HH:mm)");
        return getDate();
    }

    public LocalDateTime getNewEnd() {
        System.out.println("Enter new ending time: (yyyy-MM-dd HH:mm)");
        return getDate();
    }

    public int getNewInterval() {
        System.out.println("Enter new interval:");
        int interval;

        try {
            interval = Integer.parseInt(reader.readLine());
            if (interval <= 0) {
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("Incorrect input. Try again.\n");
            return getNewInterval();
        }

        return interval;
    }
}
