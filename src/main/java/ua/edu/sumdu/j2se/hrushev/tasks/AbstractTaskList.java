package ua.edu.sumdu.j2se.hrushev.tasks;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public abstract class AbstractTaskList implements Iterable<Task> {
    private int size;

    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to) {
        AbstractTaskList selectedTasks = TaskListFactory.createTaskList(this.getClass().toString().endsWith("ArrayTaskList") ? ListTypes.types.ARRAY : ListTypes.types.LINKED);
        int i = 0;
        if (selectedTasks.getClass().toString().equals("ArrayTaskList")) {
            while (this.size() > i) {
                if (this.getTask(i) != null){
                    if (this.getTask(i).nextTimeAfter(from) <= to && this.getTask(i).nextTimeAfter(from) > 0) {
                        selectedTasks.add(this.getTask(i));
                    }
                }
                i++;
            }

            return selectedTasks;
        } else {
            while (this.size() > i) {
                if (this.getTask(i) != null){
                    if (this.getTask(i).nextTimeAfter(from) <= to && this.getTask(i).nextTimeAfter(from) > 0) {
                        selectedTasks.add(this.getTask(i));
                    }
                }
                i++;
            }

            return selectedTasks;
        }
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Task> iterator() {
        return new CustomIterator<Task>(this);
    }

    @Override
    public void forEach(Consumer<? super Task> action) {

    }

    @Override
    public Spliterator<Task> spliterator() {
        return null;
    }
}
