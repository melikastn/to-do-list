package model;

public abstract class Task extends Subject {
    protected String name;
    protected Boolean status;

    public Task(String name) {
        this.name = name;
        status = false;
    }

    //EFFECTS: return status of task
    boolean getStatus() {
        return this.status;
    }

    //EFFECTS: return name of task
    public String getName() {
        return this.name;
    }

    //MODIFIES: this
    //EFFECTS: checks off task's status as true if it isn't already
    public void crossOff() {
        if (!this.status) {
            this.status = true;
            notifyObservers(this);
        }
    }

    //EFFECTS: return at what stage the task is
    public abstract String doneOrNot();

}
