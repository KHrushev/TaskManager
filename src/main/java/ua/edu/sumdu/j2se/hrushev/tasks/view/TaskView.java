package ua.edu.sumdu.j2se.hrushev.tasks.view;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskView implements Viewable {
    @Override
    public int view(AbstractTaskList list) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int index = -1;
        try {
            System.out.println("Enter index:");
            index = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            index = -1;
            return index;
        }

        return index;
    }

    public void displayTask(Task task) {
        System.out.println(task + "\n");
    }
}
