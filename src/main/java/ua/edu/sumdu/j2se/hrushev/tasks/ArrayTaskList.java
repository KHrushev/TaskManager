package ua.edu.sumdu.j2se.hrushev.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList implements Cloneable, Iterable<Task>{
    private int size;
    private int capacity;
    private Task[] tasks = new Task[capacity];

    public void add(Task task) {
        if (size+1 >= capacity) {
            this.grow();
        }
        tasks[size++] = task;
    }

    private void grow() {
        tasks = Arrays.copyOf(tasks, capacity+1);
        capacity++;
    }

    public int size() {
        return size;
    }

    public boolean remove(Task task) {
        for (int i = 0; i < size; i++) {
            if (task.equals(tasks[i])) {
                int numMoved = size - i - 1;
                if (numMoved > 0)
                    System.arraycopy(tasks, i+1, tasks, i, numMoved);
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
    public Stream<Task> getStream() {
        return Stream.of(this.tasks);
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "size=" + size +
                ", capacity=" + capacity +
                ", tasks=" + Arrays.toString(tasks) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;
        if (size != ((ArrayTaskList) o).size) return false;
        if (hashCode() != o.hashCode()) return false;

        ArrayTaskList that = (ArrayTaskList) o;

        for (int i = 0; i < this.size; i++) {
            if (!(this.getTask(i).equals(that.getTask(i)))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + size;
        result = 31 * result + capacity;
        for (int i = 0; i < size; i++) {
            result += tasks[i].hashCode();
        }
        return result;
    }

    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList arrayTaskList = (ArrayTaskList)super.clone();
        arrayTaskList.tasks = tasks.clone();
        return arrayTaskList;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int count = size;
            private int currentIndex;
            private int countNext;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Task next() {
                if (currentIndex < count) {
                    countNext++;
                    return tasks[currentIndex++];
                } else {
                    throw new NoSuchElementException("No such element.");
                }
            }

            @Override
            public void remove() {
                if(countNext == 0){
                    throw new IllegalStateException();
                } else {
                    ArrayTaskList.this.remove(tasks[--currentIndex]);
                    count--;
                }
            }
        };
    }
}
