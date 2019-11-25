package ua.edu.sumdu.j2se.hrushev.tasks;

public class Main {
    public static void main(String[] args) {
        Task task1 = new Task("some1", 81, 700, 23);
        Task task2 = new Task("some2", 5, 70, 15);
        Task task3 = new Task("some3", 50, 80, 5);
        Task task4 = new Task("some4", 52, 80, 2);

        task1.setActive(true);
        task2.setActive(true);
        task3.setActive(true);
        task4.setActive(true);

        AbstractTaskList tasks = new ArrayTaskList();

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        System.out.println();
    }
}
