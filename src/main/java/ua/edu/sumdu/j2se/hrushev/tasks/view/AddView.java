package ua.edu.sumdu.j2se.hrushev.tasks.view;

import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class AddView implements Viewable, DateGetter {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int view(AbstractTaskList list) {
        try {
            System.out.println("Repeatable/Single-use ?");
            String choice = reader.readLine();
            if (choice.toLowerCase().equals("repeatable")) {
                return 1;
            } else if (choice.toLowerCase().equals("single-use")) {
                return 0;
            } else {
                return -1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public Task repeatableTaskView() {
        try {
            System.out.println("Task Name:");
            String name = reader.readLine();

            System.out.println("Task Start Date and Time: (yyyy-MM-dd HH:mm)");
            LocalDateTime startDate = getDate();

            System.out.println("Task End Date and Time: (yyyy-MM-dd HH:mm)");
            LocalDateTime endDate = getDate();

            System.out.println("Interval between Task executions: (in seconds)");
            int interval = Integer.parseInt(reader.readLine());

            return new Task(name, startDate, endDate, interval);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Task singleTaskView() {
        try {
            System.out.println("Task Name:");
            String name = reader.readLine();

            System.out.println("Task start Date and Time: (yyyy-MM-dd HH:mm)");
            LocalDateTime startDate = getDate();

            return new Task(name, startDate);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
