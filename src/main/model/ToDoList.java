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
public class ToDoList implements Loadable, Saveable {
    private ArrayList<Task> todo;

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
    public void printAll() {
        for (Task t : todo) {
            t.printOne();
        }
    }

    // MODIFIES: this
    // EFFECTS: Task s is added to the To Do List
    public void insert(Task s) {
        todo.add(s);
    }

    public void save(String fileName) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
        for (Task t : todo) {
            pw.println(t.getStatus() + " " + t.getName());
        }
        pw.close();
    }

    public void load(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            Task task2 = new RegularTask(partsOfLine.get(1) + " " + partsOfLine.get(2));
            if (partsOfLine.get(0).equals("true")) {
                task2.crossOff();
            }
            insert(task2);
        }
    }

    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }


    public int size() {
        return todo.size();
    }
}