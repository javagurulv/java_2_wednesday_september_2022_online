package lv.javaguru.java2.cookingApp;

import lv.javaguru.java2.cookingApp.console_ui.*;

import java.util.Scanner;

public class CookingApplication {
    private static UIActionMap actionMap = new UIActionMap();

    public static void main(String[] args) {

        while (true) {
            printProgramMenu();
            int userChoice = getMenuNumberFromUser();
            executeSelectedMenuItem(userChoice);
        }
    }

    private static void printProgramMenu() {
        System.out.println();
        System.out.println("Program menu:");
        System.out.println("1. Add recipe to list");
        System.out.println("2. Delete recipe from list");
        System.out.println("3. Show all recipes in the list");
        System.out.println("4. Print recipe to console");
        System.out.println("5. Exit");
        System.out.println();
    }

    private static int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void executeSelectedMenuItem(int userChoice) {
        actionMap.getAction(userChoice).execute();
    }
}
