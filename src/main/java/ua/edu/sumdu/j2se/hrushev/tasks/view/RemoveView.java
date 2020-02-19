package ua.edu.sumdu.j2se.hrushev.tasks.view;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveView implements Viewable {
    @Override
    public int view(AbstractTaskList list) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String stringChoice = null;
        int choice = -1;

        try {
            System.out.println("Would you like to delete single task, or clear the list ? (Single/Clear)");
            stringChoice = reader.readLine();

            if (stringChoice.toLowerCase().equals("single")) {
                choice = 0;
            } else if (stringChoice.toLowerCase().contains("clear")) {
                choice = 1;
            } else {
                throw new IOException();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("You've entered incorrect choice, try again.\n");
            return this.view(list);
        }

        return choice;
    }

    public int removeSingle(AbstractTaskList list) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int index = -1;

        try {
            System.out.println("Enter task index to delete:");
            index = Integer.parseInt(reader.readLine());
            if (index >= list.size() || index < 0) {
                throw new IOException();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("You've entered incorrect index, try again.\n");
            return removeSingle(list);
        }

        return index;
    }
}
