package ua.edu.sumdu.j2se.hrushev.tasks;

public class Main {
    public static void main(String[] args) {
        Task task1 = new Task("some1", 2, 100, 20);
        Task task2 = new Task("some2", 76, 100, 20);
        Task task3 = new Task("some3", 10);

        ArrayTaskList tasks = new ArrayTaskList();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        System.out.println(tasks.incoming(5, 20));
    }
}
