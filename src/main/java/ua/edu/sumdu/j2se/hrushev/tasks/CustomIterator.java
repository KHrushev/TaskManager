package ua.edu.sumdu.j2se.hrushev.tasks;

import java.util.Iterator;

public class CustomIterator<T> implements Iterator {
    Task current;
    int i;

    public CustomIterator(AbstractTaskList list) {
        current = list.getTask(0);
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        return null;
    }
}
