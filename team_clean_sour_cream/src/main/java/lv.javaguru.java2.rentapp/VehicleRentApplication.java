package lv.javaguru.java2.rentapp;

import lv.javaguru.java2.rentapp.database.Database;
import lv.javaguru.java2.rentapp.database.InMemoryDatabaseImpl;

import java.util.Scanner;


public class VehicleRentApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database vehicleDB = new InMemoryDatabaseImpl();


        while (true) {
            printMainMenu();
            int userChoice = getUserChoice();
            executeUserChoice(vehicleDB, userChoice);
        }
    }

    private static void executeUserChoice(Database vehicleDB, int userChoice) {
        switch (userChoice) {
            case 1 -> {
                Scanner scanner = new Scanner(System.in);
                printSubMEnuVehicleType();
                int chosenVehicleType = getUserChoice();
                addNewVehicle(chosenVehicleType , vehicleDB);
            }
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

    private static void addNewVehicle(int chosenVehicleType, Database database) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year of production: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter color: (Black, White, Orange, Yellow, Red, Blue, Green)");
        String color = scanner.nextLine();
        System.out.println("Enter rent price per day: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter engine type: (Petrol, Diesel, Gas, Electric, Hybrid, None) ");
        String engineType = scanner.nextLine();
        System.out.println("Enter plate number: ");
        String plateNumber = scanner.nextLine();
        System.out.println("Enter transmission type: (Manual, Automatic, None) ");
        String transmissionType = scanner.nextLine();

        if (chosenVehicleType == 1) {
            System.out.println("Enter passenger amount: ");
            int passengerAmount = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter baggage amount: ");
            int baggageAmount = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter doors amount: ");
            int doorsAmount = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter is air conditioning available: (true or false) ");
            boolean isAirConditioningAvailable = Boolean.parseBoolean(scanner.nextLine());

            Vehicle passengerCar = new PassengerCar(brand, model, true, year, color, price, engineType, plateNumber, transmissionType,
                    passengerAmount, baggageAmount, doorsAmount, isAirConditioningAvailable);
            database.addNewVehicle(passengerCar);
            System.out.println("Your vehicle was added to list.");

        } else if (chosenVehicleType == 2) {
            System.out.println("Enter passenger amount: ");
            int passengerAmount = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter baggage amount: ");
            int baggageAmount = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter doors amount: ");
            int doorsAmount = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter is air conditioning available: (true or false) ");
            boolean isAirConditioningAvailable = Boolean.parseBoolean(scanner.nextLine());

            Vehicle miniBus = new MiniBus(brand, model, true, year, color, price, engineType, plateNumber, transmissionType,
                    passengerAmount, baggageAmount, doorsAmount, isAirConditioningAvailable);
            database.addNewVehicle(miniBus);
            System.out.println("Your vehicle was added to list.");

        } else if (chosenVehicleType == 3) {
            System.out.println("Enter passenger amount: ");
            int passengerAmount = Integer.parseInt(scanner.nextLine());
            Vehicle motorcycle = new Motorcycle(brand, model, false, year, color, price, engineType, plateNumber, transmissionType, passengerAmount);
            database.addNewVehicle(motorcycle);
            System.out.println("Your vehicle was added to list.");

        } else if (chosenVehicleType == 4) {
            System.out.println("Enter deck width in cm: ");
            int deckWidthInCm = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter deck length in cm: ");
            int deckLengthInCm = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter deck height in cm: ");
            int deckHeightInCm = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter trailer empty weight in kg: ");
            int emptyWeightInKg = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter trailer max load weight in kg: ");
            int maxLoadWeightInKg = Integer.parseInt(scanner.nextLine());
            Vehicle carTrailer = new CarTrailer(brand, model, false, year, color, price, engineType, plateNumber, transmissionType,
                    deckWidthInCm, deckLengthInCm, deckHeightInCm, emptyWeightInKg, maxLoadWeightInKg);
            database.addNewVehicle(carTrailer);
            System.out.println("Your vehicle was added to list.");
        }
    }

    private static void printSubMEnuVehicleType() {
        System.out.println("""
                Choose vehicle type to add
                1. Passenger Car
                2. Mini Bus
                3. Motorcycle
                4. Car Trailer""");
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

