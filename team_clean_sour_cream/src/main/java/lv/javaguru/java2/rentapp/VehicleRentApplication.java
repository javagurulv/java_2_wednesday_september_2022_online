package lv.javaguru.java2.rentapp;

import lv.javaguru.java2.rentapp.console_UI.*;

import java.util.Scanner;

class VehicleRentApplication {

    private static UIActionMap uiActionMap = new UIActionMap();

    public static void main(String[] args) {
        while (true) {
            try {
                printMainMenu();
                int userChoice = getUserChoice();
                if (userChoice > 4 || userChoice < 1) {
                    System.out.println("You must enter an integer that corresponds with a number from program menu (1 - 4)");
                } else {
                    executeUserChoice(userChoice);
                }
            } catch (NumberFormatException e) {
                System.out.println("You must enter an integer!");
            }

        }
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("Program menu:");
        System.out.println("1. Add vehicle to list");
        System.out.println("2. Delete vehicle from list by plate number");
        System.out.println("3. Show all vehicles in the list");
        System.out.println("4. Search vehicles in the list");
        System.out.println("5. Exit");
        System.out.println();
    }

    private static int getUserChoice() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine().replaceAll("[^0-9]", ""));
    }

    private static void executeUserChoice(int userChoice) {
        uiActionMap.getAction(userChoice).execute();
        System.out.println();
    }
}

