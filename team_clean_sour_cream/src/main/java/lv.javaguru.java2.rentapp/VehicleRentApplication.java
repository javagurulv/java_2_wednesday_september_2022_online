package lv.javaguru.java2.rentapp;

import lv.javaguru.java2.rentapp.console_UI.AddNewVehicleUIAction;
import lv.javaguru.java2.rentapp.console_UI.UIAction;
import lv.javaguru.java2.rentapp.database.Database;
import lv.javaguru.java2.rentapp.database.InMemoryDatabaseImpl;

import java.util.Scanner;


public class VehicleRentApplication {
    private static Database vehicleDB = new InMemoryDatabaseImpl();
    private static UIAction addNewVehicleUIAction = new AddNewVehicleUIAction(vehicleDB);

    public static void main(String[] args) {
        while (true) {
            printMainMenu();
            int userChoice = getUserChoice();
            executeUserChoice(vehicleDB, userChoice);
        }
    }

    private static void executeUserChoice(Database vehicleDB, int userChoice) {
        switch (userChoice) {
            case 1 -> addNewVehicleUIAction.execute();
            case 2 -> deleteVehicleByPlateNumber(vehicleDB);
            case 3 -> showAllVehicles(vehicleDB);
            case 4 -> exitProgram();
        }
        System.out.println("");
    }

    private static void exitProgram() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    private static void showAllVehicles(Database database) {
        System.out.println("All vehicle list: ");
        database.getAllVehicles().forEach(System.out::println);
        System.out.println("end of list.");
    }

    private static void deleteVehicleByPlateNumber(Database database) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available are:");

        database.getAllVehicles().stream()
                .map(Vehicle::getPlateNumber)
                .forEach(System.out::println);

        System.out.println("Enter vehicle plate number to delete.");
        String plateNumberToDelete = scanner.nextLine();
        database.deleteVehicleByPlateNumber(plateNumberToDelete);
        System.out.println("Your vehicle was removed from list.");
    }


    private static int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter menu item number to execute:");
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printMainMenu() {
        System.out.println("Program menu:");
        System.out.println("1. Add vehicle to list");
        System.out.println("2. Delete vehicle from list by plate number");
        System.out.println("3. Show all vehicles in the list");
        System.out.println("4. Exit");
        System.out.println("");
    }
}

