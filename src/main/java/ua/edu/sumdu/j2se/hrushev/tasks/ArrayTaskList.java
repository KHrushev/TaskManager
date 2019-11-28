package ua.edu.sumdu.j2se.hrushev.tasks;

import java.util.Arrays;

public class ArrayTaskList extends AbstractTaskList {
    private int size;
    private int capacity;
    private Task[] tasks = new Task[capacity];

    public void add(Task task) {
        if (size+1 >= capacity) {
            this.grow();
            System.out.println("I've grown ");
        }
        tasks[size++] = task;
    }

    private void grow() {
        tasks = Arrays.copyOf(tasks, capacity+5);
        capacity += 5;
    }

    public int size() {
        return size;
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

    public Task getTask(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Given index is greater than the size of array");
        } else {
            if (index >= 0 && tasks[index] != null) {
                return tasks[index];
            } else return null;
        }
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
