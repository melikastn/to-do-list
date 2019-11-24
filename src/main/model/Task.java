package model;

public abstract class Task extends Subject {
    protected String name;
    protected Boolean status;

    // creats task with name and wether its been done yet or not
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

    //EFFECTS: return string indicating the status the task is at based on the type
    public abstract String doneOrNot();

}
