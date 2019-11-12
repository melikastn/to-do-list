package model;

import java.util.ArrayList;
import java.util.List;

// Represents a subject in the observer pattern
public abstract class Subject {
    private List<ProjectObserver> observers;

    public Subject() {
        observers = new ArrayList<>();
    }

    public void addObserver(ProjectObserver o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    public void removeObserver(ProjectObserver o) {
        if (observers.contains(o)) {
            observers.remove(o);
        }
    }


    public void notifyObservers(Task t) {
        for (ProjectObserver o : observers) {
            o.update(t);
        }
    }
}
