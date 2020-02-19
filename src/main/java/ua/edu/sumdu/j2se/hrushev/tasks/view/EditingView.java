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
        try {
            int index;
            System.out.println("Enter task index to edit:");
            index = Integer.parseInt(reader.readLine());
            if (index < 0 || index > list.size()-1) {
                throw new IndexOutOfBoundsException();
            } else return index;
        } catch (IOException | IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("\nYou've entered incorrect index, try again.\n");
            return this.view(list);
        }
    }

    public int propertySelection(AbstractTaskList list, int index) {
        try {
            System.out.println("You can edit: task's name, starting time, activity status, ending time and interval, if the task is repeatable." +
                    " You can also convert single-use task into a repeatable one.\n" +
                    "Type in 'Cancel' to return to the main menu.\n");
            System.out.println("Enter property to edit:");

            String property = reader.readLine();
            int choice = -1;

            switch (property.toLowerCase()) {
                case "name":
                case "title":
                    choice = 0;
                    break;
                case "starting time":
                case "starting":
                case "start":
                    choice = 1;
                    break;
                case "ending time":
                case "ending":
                case "end":
                    choice = 2;
                    break;
                case "interval":
                    choice = 3;
                    break;
                case "convert":
                    choice = 4;
                    break;
                case "activity status":
                case "activity":
                    choice = 5;
                    break;
                case "cancel":
                    break;
                default:
                    throw new IOException();
            }

            if (((choice == 2) || (choice == 3)) && !list.getTask(index).isRepeated()) {
                throw new IOException();
            }

            return choice;
        } catch (IOException | NumberFormatException e) {
            System.out.println("There is no such property for given task. Try again.\n");
            return this.propertySelection(list, index);
        }
    }

    public String getNewName() {
        System.out.println("Enter new name:");
        try {
            return reader.readLine();
        } catch (IOException | NumberFormatException e) {
            System.out.println("You've entered incorrect name, try again.\n");
            return this.getNewName();
        }
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
        System.out.println("Enter new interval (in minutes):");
        int interval;

        try {
            interval = Integer.parseInt(reader.readLine());
            if (interval <= 0) {
                throw new IOException();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Incorrect input. Try again.\n");
            return getNewInterval();
        }

        interval = interval * 60;

        return interval;
    }
}
