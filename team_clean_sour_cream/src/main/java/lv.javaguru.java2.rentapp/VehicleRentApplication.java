package lv.javaguru.java2.rentapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class VehicleRentApplication {

    public static void main(String[] args) {

        List<Vehicle> vehiclesDataBase = new ArrayList<>();

        while (true) {
            System.out.println("Program menu:");
            System.out.println("1. Add vehicle to list");
            System.out.println("2. Delete vehicle from list by plate number");
            System.out.println("3. Show all vehicles in the list");
            System.out.println("4. Exit");

            System.out.println("");

            System.out.println("Enter menu item number to execute:");

            Scanner scanner = new Scanner(System.in);
            int userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1 -> {

                    System.out.println("""
                            Choose vehicle type to add
                            1. Passenger Car
                            2. Mini Bus
                            3. Motorcycle
                            4. Car Trailer""");

                    int chosenVehicleType = Integer.parseInt(scanner.nextLine());

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

                        vehiclesDataBase.add(passengerCar);

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

                        vehiclesDataBase.add(miniBus);

                        System.out.println("Your vehicle was added to list.");

                    } else if (chosenVehicleType == 3) {
                        System.out.println("Enter passenger amount: ");
                        int passengerAmount = Integer.parseInt(scanner.nextLine());

                        Vehicle motorcycle = new Motorcycle(brand, model, false, year, color, price, engineType, plateNumber, transmissionType, passengerAmount);

                        vehiclesDataBase.add(motorcycle);

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

                        vehiclesDataBase.add(carTrailer);

                        System.out.println("Your vehicle was added to list.");
                    }
                }
                case 2 -> {
                    System.out.println("Available are:");

                    vehiclesDataBase.stream()
                            .map(Vehicle::getPlateNumber)
                            .forEach(System.out::println);

                    System.out.println("Enter vehicle plate number to delete.");

                    String plateNumberToDelete = scanner.nextLine();

                    vehiclesDataBase.removeIf(vehicle -> plateNumberToDelete.equals(vehicle.getPlateNumber()));

                    System.out.println("Your vehicle was removed from list.");
                }
                case 3 -> {
                    System.out.println("All vehicle list: ");
                    vehiclesDataBase.forEach(System.out::println);
                    System.out.println("end of list.");
                }
                case 4 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
            }
            System.out.println("");
        }
    }
}

