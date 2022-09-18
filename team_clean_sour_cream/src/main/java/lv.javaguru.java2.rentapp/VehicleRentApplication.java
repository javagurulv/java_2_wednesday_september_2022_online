package lv.javaguru.java2.rentapp;

import lv.javaguru.java2.rentapp.console_UI.*;
import lv.javaguru.java2.rentapp.database.Database;
import lv.javaguru.java2.rentapp.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.rentapp.services.AddNewVehicleService;
import lv.javaguru.java2.rentapp.services.DeleteVehicleByPlateNumberService;
import lv.javaguru.java2.rentapp.services.ExitProgramService;
import lv.javaguru.java2.rentapp.services.ShowAllVehiclesService;

import java.util.Scanner;

class VehicleRentApplication {

    private static UIActionMap uiActionMap = new UIActionMap();

    public static void main(String[] args) {
        while (true) {
            printMainMenu();
            int userChoice = getUserChoice();
            executeUserChoice(userChoice);
        }
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("Program menu:");
        System.out.println("1. Add vehicle to list");
        System.out.println("2. Delete vehicle from list by plate number");
        System.out.println("3. Show all vehicles in the list");
        System.out.println("4. Exit");
        System.out.println();
    }

    private static int getUserChoice() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void executeUserChoice(int userChoice) {
        uiActionMap.getAction(userChoice).execute();
        System.out.println();
    }
}

