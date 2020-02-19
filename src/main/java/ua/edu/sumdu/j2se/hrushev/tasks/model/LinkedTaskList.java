package ua.edu.sumdu.j2se.hrushev.tasks.model;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList implements Cloneable, Iterable<Task>{
    private int size;
    private Node first;
    private Node last;
    private List<Observer> observers = new ArrayList<>();

    public void add(Task task) {
        if (size == 0) {
            linkFirst(task);
        } else {
            linkLast(task);
        }

        notifyObservers();
    }

    private void linkFirst(Task task) {
        Node f = first;
        Node newNode = new Node(null, task, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }

        size++;
    }

    private void linkLast(Task task) {
        Node l = last;
        Node newNode = new Node(l, task, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }

        size++;
    }

    public int size() {
        return size;
    }

    public boolean remove(Task task) {
        if (task == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    notifyObservers();
                    return true;
                }
            }
        } else {
            for(Node x = first; x != null; x = x.next) {
                if (task.equals(x.item)) {
                    unlink(x);
                    notifyObservers();
                    return true;
                }
            }
        }

        notifyObservers();
        return false;
    }

    private void unlink(Node x) {
        Node next = x.next;
        Node prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
    }

    public void clear() {
        for (Node x = first; x!= null;) {
            Node next = x.next;
            x.item = null;
            x.prev = null;
            x.next = null;
            x = next;
        }

        first = last = null;
        size = 0;
        notifyObservers();
    }

    public Task getTask(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index should not be out of lists bounds");
        }

        return getNode(index).item;
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    private Node getNode(int index) {
        if (size > 0 && index < (size / 2)) {
            Node x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    @Override
    public Stream<Task> getStream() {
        ArrayTaskList tempList = new ArrayTaskList();
        for (int i = 0; i < this.size(); i++) {
            tempList.add(this.getTask(i));
        }
        return tempList.getStream();
    }

    class Node implements Cloneable{
        Task item;
        Node next;
        Node prev;

        public Node(Node prev, Task item, Node next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{item=" + item + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return item.equals(node.item) &&
                    Objects.equals(next, node.next) &&
                    Objects.equals(prev, node.prev);
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = 31 * result + item.hashCode();
            result = 31 * result + next.hashCode();
            result = 31 * result + prev.hashCode();
            return result;
        }

        @Override
        public Node clone() throws CloneNotSupportedException {
            Node node = (Node)super.clone();
            node.item = item.clone();
            return node;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("LinkedTaskList{");
        result.append("size=").append(size).append(", ");
        for (Node i = this.first; i.next != null; i = i.next) {
            result.append("Node=").append(i.item).append(", ");
        }
        result.deleteCharAt(result.length()-2);
        result.append("}");
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedTaskList)) return false;

        LinkedTaskList that = (LinkedTaskList) o;
        Node i = that.first;
        Node j = this.first;

        while (i != null && j != null) {
            if (!(i.item.equals(j.item))) {
                return false;
            }
            i = i.next;
            j = j.next;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + size;
        for (Node i = this.first; i != null; i = i.next) {
            result = 31 * result + i.item.hashCode();
        }
        return result;
    }

    @Override
    public LinkedTaskList clone() throws CloneNotSupportedException {
        LinkedTaskList linkedTaskList = (LinkedTaskList)super.clone();
        linkedTaskList.first = first.clone();
        linkedTaskList.last = last.clone();
        return linkedTaskList;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {

            Node current = first;
            int countNext;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Task next() throws NoSuchElementException {
                if (hasNext()) {
                    Task data = current.item;
                    current = current.next;
                    countNext++;
                    return data;
                } else {
                    throw new NoSuchElementException("No such element.");
                }
            }

            @Override
            public void remove() throws IllegalStateException {
                if(countNext == 0) {
                    throw new IllegalStateException();
                } else {
                    LinkedTaskList.this.remove(this.current.prev.item);
                }
            }

            public void forEach(Consumer<? super Task> action) {
                Objects.requireNonNull(action);
                for (Iterator<Task> it = this; it.hasNext(); ) {
                    Task t = it.next();
                    action.accept(t);
                }
            }
        };
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o: observers) {
            o.update(this);
        }
    }
}
