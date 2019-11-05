package ui;

import model.*;

import java.io.IOException;
import java.util.Scanner;

public class ManageOptions {
    static ToDoList todo = new ToDoList();

    //EFFECTS: determine next step based on option
    static void nextStep(int option) throws IOException {
        Scanner userInput = new Scanner(System.in);

        if (option == 1) {
            makeTask(userInput);

        } else if (option == 2) {
            System.out.println("which item would you like to cross off?");
            todo.findAndCross(userInput.nextInt());

        } else if (option == 3) {
            System.out.println(todo.printAll());

        } else if (option == 4) {
            System.out.println("name your file:");
            todo.save(userInput.next());

        } else if (option == 5) {
            loadTodoList(userInput);
        }
    }

    private static void loadTodoList(Scanner userInput) throws IOException {
        System.out.println("which file:");
        todo.load(userInput.next());
    }

    private static void makeTask(Scanner userInput) {
        try {
            todo.notTooManyTasks();
            Task task1 = null;
            System.out.println("R : regular task\nP : priorotize task");
            String priorityOrRegular = userInput.nextLine();
            if (priorityOrRegular.equals("r") || priorityOrRegular.equals("R")) {
                System.out.println("what is the task?");
                task1 = new RegularTask(userInput.nextLine());

            } else if (priorityOrRegular.equals("P") || priorityOrRegular.equals("p")) {
                task1 = makePriorityTask(userInput);
            }
            todo.insert(task1);
        } catch (TooManyThingsToDoException e) {
            System.out.println("TOO MANY THINGS TO DO! check some tasks off before adding more tasks!");
        } finally {
            System.out.println("what would you like to do");
        }
    }

    private static Task makePriorityTask(Scanner userInput) {
        System.out.println("what is the task?");
        String name = userInput.nextLine();

        Boolean urgency = getState(userInput, "urgent");
        Boolean importance = getState(userInput, "important");
        return new PriorityTask(name, urgency, importance);
    }

    private static Boolean getState(Scanner userInput, String s) {
        String msg = "Is it " + s + "?\n y : yes\n n : no";
        System.out.println(msg);
        Boolean state = null;
        while (state == null) {
            try {
                String isUrgent = userInput.nextLine();
                state = yesOrNo(isUrgent);
            } catch (UnacceptableInputException e) {
                System.out.println("Try Again!" + " " + msg);
            }
        }
        return state;
    }

    private static Boolean yesOrNo(String whatIsState) throws UnacceptableInputException {
        Boolean yesOrNo;
        if (whatIsState.equals("y") || whatIsState.equals("Y")) {
            yesOrNo = true;
        } else if (whatIsState.equals("n") || whatIsState.equals("N")) {
            yesOrNo = false;
        } else {
            throw new UnacceptableInputException();
        }
        return yesOrNo;
    }
}
