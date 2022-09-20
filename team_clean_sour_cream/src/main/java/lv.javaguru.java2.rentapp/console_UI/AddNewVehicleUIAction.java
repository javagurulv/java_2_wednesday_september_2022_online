package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.services.add_vehicle_services.AddVehicleServiceMap;

import java.util.Scanner;

public class AddNewVehicleUIAction implements UIAction {

    private AddVehicleServiceMap serviceMap;

    public AddNewVehicleUIAction(AddVehicleServiceMap serviceMap) {
        this.serviceMap = serviceMap;
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

        switch (userChoice) {
            case 1 -> {
                AddVehicleRequest request = createPassengerCarRequest();
                serviceMap.getService(userChoice).execute(request);
            }
            case 2 -> {
                AddVehicleRequest request = createMiniBusRequest();
                serviceMap.getService(userChoice).execute(request);
            }
            case 3 -> {
                AddVehicleRequest request = createMotorcycleRequest();
                serviceMap.getService(userChoice).execute(request);
            }
            case 4 -> {
                AddVehicleRequest request = createCarTrailerRequest();
                serviceMap.getService(userChoice).execute(request);
            }
        }
    }

    private AddVehicleRequest createPassengerCarRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year of production: ");
        String year = scanner.nextLine();
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
        System.out.println("Enter passenger amount: ");
        int passengerAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter baggage amount: ");
        int baggageAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter doors amount: ");
        int doorsAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter is air conditioning available: (true or false) ");
        boolean isAirConditioningAvailable = Boolean.parseBoolean(scanner.nextLine());
        return new AddVehicleRequest(brand, model, true, year, color, price, engineType, plateNumber, transmissionType,
                passengerAmount, baggageAmount, doorsAmount, isAirConditioningAvailable);
    }

    private AddVehicleRequest createMiniBusRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year of production: ");
        String year = scanner.nextLine();
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
        System.out.println("Enter passenger amount: ");
        int passengerAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter baggage amount: ");
        int baggageAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter doors amount: ");
        int doorsAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter is air conditioning available: (true or false) ");
        boolean isAirConditioningAvailable = Boolean.parseBoolean(scanner.nextLine());
        return new AddVehicleRequest(brand, model, true, year, color, price, engineType, plateNumber, transmissionType,
                passengerAmount, baggageAmount, doorsAmount, isAirConditioningAvailable);
    }

    private AddVehicleRequest createMotorcycleRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year of production: ");
        String year = scanner.nextLine();
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
        System.out.println("Enter passenger amount: ");
        int passengerAmount = Integer.parseInt(scanner.nextLine());
        return new AddVehicleRequest(brand, model, true, year, color, price, engineType, plateNumber, transmissionType,
                passengerAmount);
    }

    private AddVehicleRequest createCarTrailerRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year of production: ");
        String year = scanner.nextLine();
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
        return new AddVehicleRequest(brand, model, true, year, color, price, engineType, plateNumber, transmissionType,
                deckWidthInCm, deckLengthInCm, deckHeightInCm, emptyWeightInKg, maxLoadWeightInKg);
    }


}
