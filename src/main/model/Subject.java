package model;

import java.util.ArrayList;
import java.util.List;

// Represents a subject in the observer pattern
public abstract class Subject {
    private List<ProjectObserver> observers;

    public Subject() {
        observers = new ArrayList<>();
    }

    //MODIFIES : this
    //EFFECTS : add observer o to subject's observers if not there already
    public void addObserver(ProjectObserver o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }
    //MODIFIES : this
    //EFFECTS : remove observer o from subject's observers if there

    public void removeObserver(ProjectObserver o) {
        if (observers.contains(o)) {
            observers.remove(o);
        }
    }


    //MODIFIES : ProjectObserver
    //EFFECTS : call update on all observers
    public void notifyObservers(Task t) {
        for (ProjectObserver o : observers) {
            o.update(t);
        }
    }
}
