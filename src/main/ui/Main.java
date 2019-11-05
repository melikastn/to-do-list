package ui;

import java.io.IOException;
import java.util.Scanner;

public class Main {

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
                ManageOptions.nextStep(option);
            } else if (!(userInput.equals("exit"))) {
                System.out.println("Try Again");
            }
        } while (!(exit.equals(userInput.nextLine())));
    }


    public static void main(String[] args) throws IOException {
        run();
    }


}
