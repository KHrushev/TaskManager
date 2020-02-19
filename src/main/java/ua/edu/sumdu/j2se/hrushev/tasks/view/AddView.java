package ua.edu.sumdu.j2se.hrushev.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.hrushev.tasks.Main;
import ua.edu.sumdu.j2se.hrushev.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hrushev.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class AddView implements Viewable, DateGetter {
    private final Logger logger = Logger.getLogger(Main.class);
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int view(AbstractTaskList list) {
        try {
            System.out.println("Repeatable/Single-use ?");
            String choice = reader.readLine();
            if (choice.toLowerCase().equals("repeatable") || choice.toLowerCase().equals("repeat")) {
                return 1;
            } else if (choice.toLowerCase().equals("single-use") || choice.toLowerCase().equals("single")) {
                return 0;
            } else {
                return -1;
            }
        } catch (IOException e) {
            logger.info("Got IOException in AddView while trying to determine whether user wants repeatable or single-use task.");
            return this.view(list);
        }
    }

    public Task repeatableTaskView() {
        try {
            System.out.println("Task Name:");
            String name = reader.readLine();

            System.out.println("Task Start Date and Time: (yyyy-MM-dd HH:mm)");
            LocalDateTime startDate = getDate();

            System.out.println("Task End Date and Time: (yyyy-MM-dd HH:mm)");
            LocalDateTime endDate = getDate();

            System.out.println("Interval between Task executions: (in minutes)");
            int interval = Integer.parseInt(reader.readLine());
            interval = interval * 60;

            if (endDate.equals(startDate) || endDate.isBefore(startDate)) {
                throw new IOException("Incorrect start-end date input.");
            }

            Task task = new Task(name, startDate, endDate, interval);
            this.confirm();
            return task;
        } catch (IOException | NumberFormatException e) {
            this.error();
            return this.repeatableTaskView();
        }
    }

    public Task singleTaskView() {
        try {
            System.out.println("Task Name:");
            String name = reader.readLine();

            System.out.println("Task start Date and Time: (yyyy-MM-dd HH:mm)");
            LocalDateTime startDate = getDate();

            Task task = new Task(name, startDate);
            this.confirm();
            return task;
        } catch (IOException | NumberFormatException e) {
            this.error();
            return this.singleTaskView();
        }
    }

    private void confirm() {
        System.out.println("Task created successfully.\n");
    }

    private void error() {
        System.out.println("You've entered incorrect data, try again.\n");
    }
}
