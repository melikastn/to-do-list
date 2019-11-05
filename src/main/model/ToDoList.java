package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//Represents a set of Tasks
public class ToDoList implements Loadable, Saveable {
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

    //EFFECTS: prints every task on the list with its status
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

    public void save(String fileName) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
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


    public void load(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./data/" + fileName));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            int size = partsOfLine.size();
            if (partsOfLine.get(0).equals("R")) {
                Task t = new RegularTask(partsOfLine.get(2));
                crossAndInsert(partsOfLine, t);
            }
            if (partsOfLine.get(0).equals("P")) {
                Task t = new PriorityTask(partsOfLine.get(4),
                        toBoolean(partsOfLine.get(3)),
                        toBoolean(partsOfLine.get(2)));
                crossAndInsert(partsOfLine, t);
            }
        }
    }

    private void crossAndInsert(ArrayList<String> partsOfLine, Task t) {
        if (partsOfLine.get(1).equals("true")) {
            t.crossOff();
        }
        insert(t);
    }

    private Boolean toBoolean(String s) {
        if (s.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }


    public int size() {
        return todo.size();
    }

    public void notTooManyTasks() throws TooManyThingsToDoException {
        if (numUncheckedTasks() >= TOO_MANY_THINGS) {
            throw new TooManyThingsToDoException();
        }
    }
}