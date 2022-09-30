package lv.javaguru.java2.esimpleforum;

import lv.javaguru.java2.esimpleforum.console_ui.*;
import lv.javaguru.java2.esimpleforum.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.esimpleforum.service.PostManagerService;
import lv.javaguru.java2.esimpleforum.service.PostValidator;

import java.util.Scanner;

public class ForumApplication {

    static PostManagerService postManagerService = new PostManagerService(
            new InMemoryDatabaseImpl(), new PostValidator()
    );


    public static void main(String[] args) {
        while (true) {
            printMenu();
            int userChoice = getUserChoice();
            executedSelectedMenuItem(userChoice);
        }
    }

    private static void executedSelectedMenuItem(int userChoice) {
        new UIActionMap(postManagerService).getAction(userChoice).execute();
    }

    private static int getUserChoice() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);

        return  (scanner.hasNextLine()) ? Integer.parseInt(scanner.nextLine()) : 4;
    }

    private static void printMenu() {
        System.out.println("Program menu:");
        System.out.println("1. Add posts to list");
        System.out.println("2. Delete post from list");
        System.out.println("3. Show all posts in the list");
        System.out.println("4. Exit");
    }
}
