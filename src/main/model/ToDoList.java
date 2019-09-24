package model;

import java.util.ArrayList;
import java.util.Scanner;

//Represents a set of Tasks
public class ToDoList {
    private static ArrayList<Task> todo;

    //EFFECTS: set is empty
    public ToDoList() {
        todo = new ArrayList<>();
    }


    public static void run() {
        String exit;
        exit = "exit";
        System.out.println("Welcome to Melika's To Do List!");
        Scanner userInput = new Scanner(System.in);
        System.out.println("what would you like to do");
        do {
            System.out.println("[1] add a to do list item" + " [2] cross off an item"
                    + "[3] show all the items" + " or exit");
            if (userInput.hasNextInt()) {
                int option = userInput.nextInt();
                nextStep(option);
            } else if (!(userInput.equals("exit"))) {
                System.out.println("Try Again");
            }
        } while (!(exit.equals(userInput.nextLine())));
    }


    //EFFECTS: determine next step based on option
    private static void nextStep(int option) {
        Scanner userInput = new Scanner(System.in);
        if (option == 1) {
            System.out.println("type the task you want to add and press enter.");
            Task task1 = new Task(userInput.nextLine());
            insert(task1);

        } else if (option == 2) {
            System.out.println("which item would you like to cross off?");
            findAndCross(userInput.nextInt());
        } else if (option == 3) {
            printAll();

        } else {
            System.out.println("try again!");

        }
    }

    //REQUIRES: that item must be on the list
    //EFFECTS: find i'th task on the list and check it off as done
    //MODIFIES: Task
    public static void findAndCross(int i) {
        int x = i - 1;
        Task t = todo.get(x);
        t.crossOff();
    }

    //EFFECTS: prints every task on the list with its status
    public static void printAll() {
        for (Task t : todo) {
            t.printOne();
        }
    }

    // MODIFIES: this
    // EFFECTS: Task s is added to the To Do List
    public static void insert(Task s) {
        todo.add(s);
    }

    /*
    // REQUIRES: String s is an element of the ListofString
    // MODIFIES: this
    // EFFECTS: String s is removed from the ListofString
    public void remove(Task s) { todremove(s); }

     */


}
