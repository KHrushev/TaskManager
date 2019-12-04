package ua.edu.sumdu.j2se.hrushev.tasks;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Task task1 = new Task("some1", 81);
        Task task2 = task1.clone();

        System.out.println(task1);
        System.out.println(task2);
        System.out.println(task1.clone().equals(task1));
    }
}
