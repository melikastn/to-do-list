package model;

//Represents a task and whether it's been done yet or not
public class RegularTask extends Task {

    //construct a task with given name and status false.
    public RegularTask(String name) {
        super(name);
    }


    //EFFECTS: if status is true return checked box, " O" otherwise
    @Override
    public String doneOrNot() {
        if (getStatus()) {
            return " ☑";
        } else {
            return  " O";
        }
    }


}