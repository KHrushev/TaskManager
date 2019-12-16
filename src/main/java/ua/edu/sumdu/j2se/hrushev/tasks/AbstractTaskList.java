package ua.edu.sumdu.j2se.hrushev.tasks;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>{
    private int size;

    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract Task getTask(int index);
    public abstract Stream<Task> getStream();

    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) {
        AbstractTaskList selectedTasks = TaskListFactory.createTaskList(this instanceof ArrayTaskList ? ListTypes.types.ARRAY : ListTypes.types.LINKED);

        this.getStream().filter(task -> task != null && task.nextTimeAfter(from) != null && task.nextTimeAfter(from).isBefore(to) && task.isActive())
                .forEach(selectedTasks::add);

        return selectedTasks;
    }

    public abstract int size();
}
