package model;

public class PriorityTask extends Task {
    private Boolean urgent;
    private Boolean important;

    //construct a task with given name and status false.
    public PriorityTask(String name, Boolean urgent, Boolean important) {
        super(name);
        this.urgent = urgent;
        this.important = important;
    }

    //EFFECTS: change urgency
    public void changeUrgency() {
        this.urgent = !urgent;
    }

    //EFFECTS: change importance
    public void changeImportance() {
        this.important = !important;
    }

    //EFFECTS: return urgent or not
    public boolean getUrgency() {
        return this.urgent;
    }

    //EFFECTS: return important or not
    public boolean getImportance() {
        return this.important;
    }

    @Override
    //EFFECTS: if status is true return checkmark, emptybox with suggestion otherwise
    public String doneOrNot() {
        if (getStatus()) {
            return " ☑";
        } else {
            return " O  -- " + this.suggest();
        }
    }

    //EFFECTS: according to level of urgency and importance give suggestions
    public String suggest() {
        if (this.important && this.urgent) {
            return "Do it now!!!";
        } else if (this.urgent) {
            return "get someone to do it for you";
        } else if (this.important) {
            return "plan to do it soon!";
        } else {
            return "don't worry about it for now";
        }

    }


}
