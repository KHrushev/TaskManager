package ua.edu.sumdu.j2se.hrushev.tasks.model;

public interface Observable {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
