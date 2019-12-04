package ua.edu.sumdu.j2se.hrushev.tasks;

public abstract class AbstractTaskList {
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

    public abstract int size();
}
