package model;

// Represents an observer of Task
public interface ProjectObserver {

    // EFFECTS: responds to an item getting checked off
    void update(Task t);
}
