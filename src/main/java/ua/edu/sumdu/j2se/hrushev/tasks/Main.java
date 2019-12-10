package ua.edu.sumdu.j2se.hrushev.tasks;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Task task1 = new Task("some1", 81);
        Task task2 = task1.clone();
        Task task3 = new Task("some2", 21);
        Task task4 = new Task("some3", 85);
        Task task5 = new Task("some4", 81);


        ArrayTaskList list = new ArrayTaskList();
        list.add(task1);
        list.add(task2);
        list.add(task3);
        list.add(task4);
        list.add(task5);

        System.out.println(list.incoming(40, 90));
    }
}
