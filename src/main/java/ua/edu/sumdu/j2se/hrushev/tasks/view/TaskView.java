package ua.edu.sumdu.j2se.hrushev.tasks.view;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskView implements Viewable {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int view(AbstractTaskList list) {
        int index;

        if (list.size() == 0) {
            System.out.println("\nThere are to tasks to display.\n");
            return -1;
        } else {
            try {
                System.out.println("Enter task index to display:");
                index = Integer.parseInt(reader.readLine());
                if (index < 0 || index > list.size()-1) {
                    throw new IndexOutOfBoundsException();
                } else {
                    this.displayTask(list.getTask(index));
                }
            } catch (IOException | IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("\nYou've entered incorrect index, try again.\n");
                return this.view(list);
            }
            return 0;
        }
    }

    public void displayTask(Task task) {
        System.out.println("Chosen Task:  " + task + "\n");
    }
}
