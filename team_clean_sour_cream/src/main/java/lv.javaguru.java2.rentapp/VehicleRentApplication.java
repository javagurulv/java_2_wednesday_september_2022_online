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

    private static Database vehicleDB = new InMemoryDatabaseImpl();
    private static AddNewVehicleService addNewVehicleService = new AddNewVehicleService(vehicleDB);
    private static DeleteVehicleByPlateNumberService deleteVehicleByPlateNumberService = new DeleteVehicleByPlateNumberService(vehicleDB);
    private static ShowAllVehiclesService showAllVehiclesService = new ShowAllVehiclesService(vehicleDB);
    private static ExitProgramService exitProgramService = new ExitProgramService();
    private static UIAction addNewVehicleUIAction = new AddNewVehicleUIAction(addNewVehicleService);
    private static UIAction deleteVehicleByPlateNumberUIAction = new DeleteVehicleByPlateNumberUIAction(deleteVehicleByPlateNumberService);
    private static UIAction showAllVehicleUIAction = new ShowAllVehiclesUIAction(showAllVehiclesService);
    private static UIAction exitProgramUIAction = new ExitProgramUIAction(exitProgramService);

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
        switch (userChoice) {
            case 1 -> addNewVehicleUIAction.execute();
            case 2 -> deleteVehicleByPlateNumberUIAction.execute();
            case 3 -> showAllVehicleUIAction.execute();
            case 4 -> exitProgramUIAction.execute();
        }
        System.out.println();
    }
}

