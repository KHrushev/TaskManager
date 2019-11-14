package ua.edu.sumdu.j2se.hrushev.tasks;

import java.util.ArrayList;

public class ArrayTaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    public boolean remove(Task task) {
        if (tasks.contains(task)) {
            tasks.remove(task);
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList selectedTasks = new ArrayTaskList();
        for (Task i: tasks) {
            if ( (i.getTime() > from && i.getTime() < to) || (i.getStartTime() > from && i.getStartTime() < to)) {
                if (i.isActive()){
                    selectedTasks.add(i);
                }
            }
        }

        return selectedTasks;
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "tasks=" + tasks +
                '}';
    }
}
