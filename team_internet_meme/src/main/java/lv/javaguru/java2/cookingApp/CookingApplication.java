package lv.javaguru.java2.cookingApp;

import lv.javaguru.java2.cookingApp.console_ui.*;

import java.util.Scanner;

public class CookingApplication {
    private static UIActionMap actionMap = new UIActionMap();

    public static void main(String[] args) {

        while (true) {
            try {
                printProgramMenu();
                int userChoice = getMenuNumberFromUser();
                if (userChoice < 1 || userChoice > 6) {
                    System.out.println("You must enter an integer that corresponds with one of the menu options");
                } else {
                    executeSelectedMenuItem(userChoice);
                }
            } catch (NumberFormatException e) {
                System.out.println("You must enter an integer!");
            }
        }
    }

    private static void printProgramMenu() {
        System.out.println();
        System.out.println("Program menu:");
        System.out.println("1. Add recipe to list");
        System.out.println("2. Delete recipe from list");
        System.out.println("3. Show all recipes in the list");
        System.out.println("4. Print recipe to console");
        System.out.println("5. Search recipes by ingredients");
        System.out.println("6. Exit");
        System.out.println();
    }

    private static int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine().replaceAll("[^0-9]", ""));
    }

    private static void executeSelectedMenuItem(int userChoice) {
        actionMap.getAction(userChoice).execute();
    }
}
