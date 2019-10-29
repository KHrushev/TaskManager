package ua.edu.sumdu.j2se.hrushev.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main utility class.
 * Marked final and created private constructor
 * so other classes cannot extend it.
 */

public final class Main {
    /**
     * Main method. Program starting point
     *
     * @param args array of String objects
     */
    public static void main(final String[] args) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String userTitle;
        int userStartTime;
        int userEndTime;
        int userInterval;
        int current;

        try {
            System.out.println("Enter title: ");
            userTitle = reader.readLine();
            System.out.println("Enter start time: ");
            userStartTime = Integer.parseInt(reader.readLine());
            System.out.println("Enter end time: ");
            userEndTime = Integer.parseInt(reader.readLine());
            System.out.println("Enter interval: ");
            userInterval = Integer.parseInt(reader.readLine());
            System.out.println("Current time(int): ");
            current = Integer.parseInt(reader.readLine());

            Task task = new Task(userTitle, userStartTime,
                    userEndTime, userInterval);

            System.out.println(task.nextTimeAfter(current));
        } catch (IOException ioExc) {
            System.out.println("Exception occurred while"
                    + "trying to enter your input. "
                    + "All numbers have to"
                    + "be greater than 0. "
                    + "End time has to be"
                    + " greater than start time.");
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Exception occurred while"
                    + "trying to enter your input. "
                    + "Everything, besides title,"
                    + " has to be a number. "
                    + "All numbers have to"
                    + " be greater than 0. "
                    + "End time has to"
                    + " be greater than start time.");
        }

    }

    /**
     * Private constructor so other classes cannot extend Main class.
     */
    private Main() { }
}
