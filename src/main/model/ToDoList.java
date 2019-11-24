package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//Represents a set of Tasks
public class ToDoList implements Loadable, Saveable, ProjectObserver {
    private ArrayList<Task> todo;
    public static final int TOO_MANY_THINGS = 10;

    //EFFECTS: set is empty
    public ToDoList() {
        todo = new ArrayList<>();
    }


    //REQUIRES: that item must be on the list
    //EFFECTS: find i'th task on the list and check it off as done
    //MODIFIES: Task
    public void findAndCross(int i) {
        int x = i - 1;
        Task t = getTask(x);
        t.crossOff();
    }


    public Task getTask(int i) {
        return todo.get(i);
    }

    //EFFECTS: construct and return a string of every task on the list with its status
    public String printAll() {
        String listOfAll = "";
        for (Task t : todo) {
            listOfAll = listOfAll + (t.getName() + t.doneOrNot()) + "\n";
        }
        return listOfAll;
    }

    // MODIFIES: this
    // EFFECTS: Task s is added to the To Do List
    public void insert(Task s) {
        todo.add(s);
        s.addObserver(this);
    }

    //EFFECTS: return number of unchecked tasks in to do list
    public int numUncheckedTasks() {
        int numUncheckedTasks = 0;
        for (Task t : todo) {
            if (!t.getStatus()) {
                numUncheckedTasks++;
            }
        }
        return numUncheckedTasks;
    }

    //MODIFIES : files
    //EFFECTS : write every task to a text file with given name
    public void save(String fileName) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream("./data/" + fileName));
        for (Task t : todo) {
            if (t instanceof RegularTask) {
                pw.println("R" + " " + t.getStatus() + " " + t.getName());
            } else if (t instanceof PriorityTask) {
                PriorityTask pt = (PriorityTask) t;
                pw.println("P" + " " + pt.getStatus() + " " + pt.getImportance() + " "
                        + pt.getUrgency() + " " + pt.getName());
            }
        }
        pw.close();
    }

    //EFFECTS: load the given file by making a todoList of tasks, throw exception if file not found
    public void load(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./data/" + fileName));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            if (partsOfLine.get(0).equals("R")) {
                Task t = new RegularTask(makeName(partsOfLine,false));
                crossAndInsert(partsOfLine, t);
            }
            if (partsOfLine.get(0).equals("P")) {
                Task t = new PriorityTask(makeName(partsOfLine, true),
                        toBoolean(partsOfLine.get(3)),
                        toBoolean(partsOfLine.get(2)));
                crossAndInsert(partsOfLine, t);
            }
        }
    }

    //helper for load
    private String makeName(ArrayList<String> list, Boolean isPriorityTask) {
        ArrayList<String> duplicate = new ArrayList<>();
        for (String s : list) {
            duplicate.add(s);
        }
        helperMakeName(isPriorityTask, duplicate);
        String name = "";
        for (String s : duplicate) {
            if (name == "") {
                name = s;
            } else {
                name = name + " " + s;
            }
        }
        return name;
    }

    private void helperMakeName(Boolean isPriorityTask, ArrayList<String> duplicate) {
        duplicate.remove(0);
        duplicate.remove(0);
        if (isPriorityTask) {
            duplicate.remove(0);
            duplicate.remove(0);
        }
    }

    //helper for load
    private void crossAndInsert(ArrayList<String> partsOfLine, Task t) {
        if (partsOfLine.get(1).equals("true")) {
            t.crossOff();
        }
        insert(t);
    }

    //helper for load
    private Boolean toBoolean(String s) {
        if (s.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    // helper for load
    private ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }


    //EFFECT : return number of items in todolist
    public int size() {
        return todo.size();
    }

    //EFFECTS : throw TooManyThingToDoException if there are number of unckecked tasks exceed the limit
    public void notTooManyTasks() throws TooManyThingsToDoException {
        if (numUncheckedTasks() >= TOO_MANY_THINGS) {
            throw new TooManyThingsToDoException();
        }
    }

    // EFFECTS: prints message to acknowledge notification that task was checked off
    @Override
    public void update(Task t) {
        System.out.println("Good job with " + t.getName());
    }
}