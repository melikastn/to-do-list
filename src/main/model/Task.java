package model;

//Represents a task and whether it's been done yet or not
class Task {
    private String name;
    private Boolean status;

    //construct a task with given name and status false.
    public Task(String name) {
        this.name = name;
        status = false;
    }

    //EFFECTS: return status of task
    boolean getStatus() {
        return this.status;
    }


    //EFFECTS: return name of task
    private String getName() {
        return this.name;
    }

    //MODIFIES: this
    //EFFECTS: checks off task's status as true if it isn't already
    public void crossOff() {
        if (!this.status) {
            this.status = true;
        }
    }

    //EFFECTS: if status is true return "done","not done yet!" otherwise
    public String doneOrNot() {
        if (getStatus()) {
            return "done!";
        } else {
            return "not done yet!";
        }
    }


    //EFFECTS: print the name of the task and whether it's done or not
    public void printOne() {
        System.out.println(getName() + " is " + doneOrNot());
    }
}
