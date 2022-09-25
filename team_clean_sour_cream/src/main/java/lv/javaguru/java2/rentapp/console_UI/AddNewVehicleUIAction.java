package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.AddNewVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddNewVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.AddNewVehicleService;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.Scanner;

public class AddNewVehicleUIAction implements UIAction {

    private AddNewVehicleService addNewVehicleService;

    public AddNewVehicleUIAction(AddNewVehicleService addNewVehicleService) {
        this.addNewVehicleService = addNewVehicleService;
    }

    @Override
    public void execute() {

        printVehicleTypeToAddChooseMenu();

        int userChoice = getUserChoice();

        AddNewVehicleResponse addNewVehicleResponse = executeUserChoice(userChoice);

        if (addNewVehicleResponse.hasErrors()) {
            addNewVehicleResponse.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("New vehicle id was: " + addNewVehicleResponse.getNewVehicle().getId());
            System.out.println("Your vehicle was added to list.");
        }
    }

    private void printVehicleTypeToAddChooseMenu() {
        System.out.println("""
                Choose vehicle type to add
                1. Passenger Car
                2. Mini Bus
                3. Motorcycle
                4. Car Trailer""");
    }

    private int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private AddNewVehicleResponse executeUserChoice(int userChoice) {
        AddNewVehicleResponse addNewVehicleResponse = null;
        switch (userChoice) {
            case 1 -> {
                AddNewVehicleRequest request = createPassengerCarRequest();
                addNewVehicleResponse = addNewVehicleService.execute(request);
            }
            case 2 -> {
                AddNewVehicleRequest request = createMiniBusRequest();
                addNewVehicleResponse = addNewVehicleService.execute(request);
            }
            case 3 -> {
                AddNewVehicleRequest request = createMotorcycleRequest();
                addNewVehicleResponse = addNewVehicleService.execute(request);
            }
            case 4 -> {
                AddNewVehicleRequest request = createCarTrailerRequest();
                addNewVehicleResponse = addNewVehicleService.execute(request);
            }
        }
        return addNewVehicleResponse;
    }

    private AddNewVehicleRequest createPassengerCarRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year of production: ");
        Integer year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter color: (Black, White, Orange, Yellow, Red, Blue, Green)");
        Colour color = Colour.valueOf(scanner.nextLine().toUpperCase().replaceAll("[^a-zA-Z]", ""));
        System.out.println("Enter rent price per day: ");
        Double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter engine type: (Petrol, Diesel, Gas, Electric, Hybrid, None) ");
        EngineType engineType = EngineType.valueOf(scanner.nextLine().toUpperCase().replaceAll("[^a-zA-Z]", ""));
        System.out.println("Enter plate number: ");
        String plateNumber = scanner.nextLine();
        System.out.println("Enter transmission type: (Manual, Automatic, None) ");
        TransmissionType transmissionType = TransmissionType.valueOf(scanner.nextLine().toUpperCase().replaceAll("[^a-zA-Z]", ""));

        System.out.println("Enter passenger amount: ");
        Integer passengerAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter baggage amount: ");
        Integer baggageAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter doors amount: ");
        Integer doorsAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter is air conditioning available: (true or false) ");
        boolean isAirConditioningAvailable = Boolean.parseBoolean(scanner.nextLine());

        return AddNewVehicleRequest.builder()
                .vehicleType(VehicleType.PASSENGER_CAR)
                .brand(brand)
                .model(model)
                .isAvailableForRent(true)
                .yearOfProduction(year)
                .colour(color)
                .rentPricePerDay(price)
                .engineType(engineType)
                .plateNumber(plateNumber)
                .transmissionType(transmissionType)
                .passengerAmount(passengerAmount)
                .baggageAmount(baggageAmount)
                .doorsAmount(doorsAmount)
                .isAirConditioningAvailable(isAirConditioningAvailable)
                .build();
    }

    private AddNewVehicleRequest createMiniBusRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year of production: ");
        Integer year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter color: (Black, White, Orange, Yellow, Red, Blue, Green)");
        Colour color = Colour.valueOf(scanner.nextLine().toUpperCase().replaceAll("[^a-zA-Z]", ""));
        System.out.println("Enter rent price per day: ");
        Double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter engine type: (Petrol, Diesel, Gas, Electric, Hybrid, None) ");
        EngineType engineType = EngineType.valueOf(scanner.nextLine().toUpperCase().replaceAll("[^a-zA-Z]", ""));
        System.out.println("Enter plate number: ");
        String plateNumber = scanner.nextLine();
        System.out.println("Enter transmission type: (Manual, Automatic, None) ");
        TransmissionType transmissionType = TransmissionType.valueOf(scanner.nextLine().toUpperCase().replaceAll("[^a-zA-Z]", ""));

        System.out.println("Enter passenger amount: ");
        Integer passengerAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter baggage amount: ");
        Integer baggageAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter doors amount: ");
        Integer doorsAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter is air conditioning available: (true or false) ");
        boolean isAirConditioningAvailable = Boolean.parseBoolean(scanner.nextLine());

        return AddNewVehicleRequest.builder()
                .vehicleType(VehicleType.MINIBUS)
                .brand(brand)
                .model(model)
                .isAvailableForRent(true)
                .yearOfProduction(year)
                .colour(color)
                .rentPricePerDay(price)
                .engineType(engineType)
                .plateNumber(plateNumber)
                .transmissionType(transmissionType)
                .passengerAmount(passengerAmount)
                .baggageAmount(baggageAmount)
                .doorsAmount(doorsAmount)
                .isAirConditioningAvailable(isAirConditioningAvailable)
                .build();
    }

    private AddNewVehicleRequest createMotorcycleRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year of production: ");
        Integer year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter color: (Black, White, Orange, Yellow, Red, Blue, Green)");
        Colour color = Colour.valueOf(scanner.nextLine().toUpperCase().replaceAll("[^a-zA-Z]", ""));
        System.out.println("Enter rent price per day: ");
        Double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter engine type: (Petrol, Diesel, Gas, Electric, Hybrid, None) ");
        EngineType engineType = EngineType.valueOf(scanner.nextLine().toUpperCase().replaceAll("[^a-zA-Z]", ""));
        System.out.println("Enter plate number: ");
        String plateNumber = scanner.nextLine();
        System.out.println("Enter transmission type: (Manual, Automatic, None) ");
        TransmissionType transmissionType = TransmissionType.valueOf(scanner.nextLine().toUpperCase().replaceAll("[^a-zA-Z]", ""));

        System.out.println("Enter passenger amount: ");
        Integer passengerAmount = Integer.parseInt(scanner.nextLine());

        return AddNewVehicleRequest.builder()
                .vehicleType(VehicleType.MOTORCYCLE)
                .brand(brand)
                .model(model)
                .isAvailableForRent(true)
                .yearOfProduction(year)
                .colour(color)
                .rentPricePerDay(price)
                .engineType(engineType)
                .plateNumber(plateNumber)
                .transmissionType(transmissionType)
                .passengerAmount(passengerAmount)
                .build();
    }

    private AddNewVehicleRequest createCarTrailerRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year of production: ");
        Integer year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter color: (Black, White, Orange, Yellow, Red, Blue, Green)");
        Colour color = Colour.valueOf(scanner.nextLine().toUpperCase().replaceAll("[^a-zA-Z]", ""));
        System.out.println("Enter rent price per day: ");
        Double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter engine type: (Petrol, Diesel, Gas, Electric, Hybrid, None) ");
        EngineType engineType = EngineType.valueOf(scanner.nextLine().toUpperCase().replaceAll("[^a-zA-Z]", ""));
        System.out.println("Enter plate number: ");
        String plateNumber = scanner.nextLine();
        System.out.println("Enter transmission type: (Manual, Automatic, None) ");
        TransmissionType transmissionType = TransmissionType.valueOf(scanner.nextLine().toUpperCase().replaceAll("[^a-zA-Z]", ""));

        System.out.println("Enter deck width in cm: ");
        Integer deckWidthInCm = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter deck length in cm: ");
        Integer deckLengthInCm = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter deck  height in cm: ");
        Integer deckHeightInCm = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter trailer empty weight in kg: ");
        Integer emptyWeightInKg = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter trailer max load weight in kg: ");
        Integer maxLoadWeightInKg = Integer.parseInt(scanner.nextLine());

        return AddNewVehicleRequest.builder()
                .vehicleType(VehicleType.CAR_TRAILER)
                .brand(brand).model(model)
                .isAvailableForRent(true)
                .yearOfProduction(year)
                .colour(color)
                .rentPricePerDay(price)
                .engineType(engineType)
                .plateNumber(plateNumber)
                .transmissionType(transmissionType)
                .deckWidthInCm(deckWidthInCm)
                .deckLengthInCm(deckLengthInCm)
                .deckHeightInCm(deckHeightInCm)
                .emptyWeightInKg(emptyWeightInKg)
                .maxLoadWeightInKg(maxLoadWeightInKg)
                .build();
    }
}
