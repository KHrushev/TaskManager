package ua.edu.sumdu.j2se.hrushev.tasks;

public class LinkedTaskList {
    private int size;
    public Node first;
    public Node last;

    public void add(Task task) {
        if (size == 0) {
            linkFirst(task);
        } else {
            linkLast(task);
        }
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

    public boolean remove(Task task) {
        if (task == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for(Node x = first; x != null; x = x.next) {
                if (task.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }

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

    public Task getTask(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index should go out of list bounds");
        }
        Node current = first;
        for (int i = 0; i <= index; i++) {
            current = current.next;
        }

        return current.item;
    }

    public int size() {
        return size;
    }

    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList selectedTasks = new LinkedTaskList();

        for (Node i = first; i != null; i = i.next) {
            if ( (i.item.getTime() > from && i.item.getTime() < to) || (i.item.getStartTime() > from && i.item.getStartTime() < to) ) {
                if (i.item.isActive()){
                    selectedTasks.add(i.item);
                }
            }
        }

        return selectedTasks;
    }

    class Node {
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
    }

    @Override
    public String toString() {
        return "LinkedTaskList{" +
                "size=" + size +
                ", first=" + first +
                ", last=" + last +
                '}';
    }
}
