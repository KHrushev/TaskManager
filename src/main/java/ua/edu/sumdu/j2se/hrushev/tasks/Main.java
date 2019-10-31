package ua.edu.sumdu.j2se.hrushev.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class Main {
    public static void main(final String[] args) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        /*String userTitle;
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
        }*/

        Task test = new Task("Task Title", 5, 25, 3);

        test.setTime(42);

	    System.out.println(test.getTitle());
	    System.out.println(test.getStartTime()  + " startime");
	    System.out.println(test.getEndTime() + " endtime");
	    System.out.println(test.getRepeatInterval() + " repeatint");
	    System.out.println(test.getTime() + " time");
	    System.out.println(test.isRepeated() + " isRepeated");
	    System.out.println(test.isActive() + " isActive\n");

//	    test.setTime(5, 25, 3);
//
//	    System.out.println(test.getTitle());
//	    System.out.println(test.getStartTime()  + " startime");
//	    System.out.println(test.getEndTime() + " endtime");
//	    System.out.println(test.getRepeatInterval() + " repeatint");
//	    System.out.println(test.getTime() + " time");
//	    System.out.println(test.isRepeated() + " isRepeated");
//	    System.out.println(test.isActive() + " isActive");
    }

    private Main() { }
}
