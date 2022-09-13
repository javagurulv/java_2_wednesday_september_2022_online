package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.*;
import lv.javaguru.java2.rentapp.services.AddNewVehicleService;
import java.util.Scanner;

public class AddNewVehicleUIAction implements UIAction {

    private AddNewVehicleService addNewVehicleService;

    public AddNewVehicleUIAction(AddNewVehicleService addNewVehicleService) {
        this.addNewVehicleService = addNewVehicleService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                Choose vehicle type to add
                1. Passenger Car
                2. Mini Bus
                3. Motorcycle
                4. Car Trailer""");

        int userChoice = Integer.parseInt(scanner.nextLine());

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

        if (userChoice == 1) {
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
            addNewVehicleService.execute(passengerCar);

            System.out.println("Your vehicle was added to list.");

        } else if (userChoice == 2) {
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
            addNewVehicleService.execute(miniBus);

            System.out.println("Your vehicle was added to list.");

        } else if (userChoice == 3) {
            System.out.println("Enter passenger amount: ");
            int passengerAmount = Integer.parseInt(scanner.nextLine());

            Vehicle motorcycle = new Motorcycle(brand, model, false, year, color, price, engineType, plateNumber, transmissionType, passengerAmount);
            addNewVehicleService.execute(motorcycle);

            System.out.println("Your vehicle was added to list.");

        } else if (userChoice == 4) {
            System.out.println("Enter deck width in cm: ");
            int deckWidthInCm = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter deck length in cm: ");
            int deckLengthInCm = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter deck  height in cm: ");
            int deckHeightInCm = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter trailer empty weight in kg: ");
            int emptyWeightInKg = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter trailer max load weight in kg: ");
            int maxLoadWeightInKg = Integer.parseInt(scanner.nextLine());

            Vehicle carTrailer = new CarTrailer(brand, model, false, year, color, price, engineType, plateNumber, transmissionType,
                    deckWidthInCm, deckLengthInCm, deckHeightInCm, emptyWeightInKg, maxLoadWeightInKg);
            addNewVehicleService.execute(carTrailer);

            System.out.println("Your vehicle was added to list.");
        }
    }
}
