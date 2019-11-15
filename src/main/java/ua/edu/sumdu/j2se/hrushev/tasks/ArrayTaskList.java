package ua.edu.sumdu.j2se.hrushev.tasks;

import java.util.Arrays;

public class ArrayTaskList {
    private int size;
    private int capacity;
    private Task[] tasks = new Task[capacity];

    public void add(Task task) {
        if (size+1 >= capacity) {
            this.grow();
            System.out.println("I've grown!");
        }
        tasks[size++] = task;
    }

    private void grow() {
        tasks = Arrays.copyOf(tasks, capacity+5);
        capacity += 5;
    }

    public boolean remove(Task task) {
        for (int i = 0; i < size; i++) {
            if (task.equals(tasks[i])) {
                int numMoved = size - i - 1;
                if (numMoved > 0)
                    System.arraycopy(tasks, i+1, tasks, i,
                            numMoved);
                System.out.println(Arrays.toString(tasks));
                tasks[--size] = null;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public Task getTask(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Given index is greater than the size of array");
        } else {
            if (index >= 0 && tasks[index] != null) {
                return tasks[index];
            } else return null;
        }
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList selectedTasks = new ArrayTaskList();
        for (Task i: tasks) {
            if (i != null){
                if ( (i.getTime() > from && i.getTime() < to) || (i.getStartTime() > from && i.getStartTime() < to) ) {
                    if (i.isActive()){
                        selectedTasks.add(i);
                    }
                }
            }
        }

        return selectedTasks;
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "size=" + size +
                ", capacity=" + capacity +
                ", tasks=" + Arrays.toString(tasks) +
                '}';
    }
}
