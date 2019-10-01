package ui;

import model.Task;
import model.ToDoList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static String x;
    static ToDoList todo = new ToDoList();

    public static void run() throws IOException {
        String exit;
        exit = "exit";
        System.out.println("Welcome to Melika's To Do List!");
        Scanner userInput = new Scanner(System.in);
        System.out.println("what would you like to do");
        do {
            System.out.println("[1] add a to do list item" + " [2] cross off an item"
                    + "[3] show all the items" + "[4] save list [5] load list" + " or exit");
            if (userInput.hasNextInt()) {
                int option = userInput.nextInt();
                nextStep(option);
            } else if (!(userInput.equals("exit"))) {
                System.out.println("Try Again");
            }
        } while (!(exit.equals(userInput.nextLine())));
    }

    //EFFECTS: determine next step based on option
    public static void nextStep(int option) throws IOException {
        Scanner userInput = new Scanner(System.in);

        if (option == 1) {
            System.out.println("type the task you want to add and press enter.");
            Task task1;
            task1 = new Task(userInput.nextLine());
            todo.insert(task1);

        } else if (option == 2) {
            System.out.println("which item would you like to cross off?");
            todo.findAndCross(userInput.nextInt());

        } else if (option == 3) {
            todo.printAll();

        } else if (option == 4) {
            System.out.println("name you file:");
            todo.save(userInput.next());

        } else if (option == 5) {
            System.out.println("which file:");
            todo.load(userInput.next());
        }
    }

    public static void main(String[] args) throws IOException {
        run();
    }


}
