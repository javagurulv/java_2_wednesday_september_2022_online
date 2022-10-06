package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.AddVehicleService;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.time.LocalDate;
import java.util.Scanner;

import static lv.javaguru.java2.rentapp.domain.CarTrailer.*;
import static lv.javaguru.java2.rentapp.domain.MiniBus.*;
import static lv.javaguru.java2.rentapp.domain.Motorcycle.MOTO_MAX_PASSENGER_AMOUNT;
import static lv.javaguru.java2.rentapp.domain.Motorcycle.MOTO_MIN_PASSENGER_AMOUNT;
import static lv.javaguru.java2.rentapp.domain.PassengerCar.*;
import static lv.javaguru.java2.rentapp.domain.Vehicle.MAX_ALLOWED_CURRENT_YEAR_BACKWARD_REDUCER;

public class AddVehicleUIAction implements UIAction {

    Scanner scanner = new Scanner(System.in);

    private AddVehicleService addVehicleService;

    public AddVehicleUIAction(AddVehicleService addNewVehicleService) {
        this.addVehicleService = addNewVehicleService;
    }

    @Override
    public void execute() {

        printVehicleTypeMenu();
        try {
            int userChoice = getUserChoice();

            if (userChoice > VehicleType.values().length || userChoice < 1) {
                System.out.println("You must enter a number from program menu (1 - " + VehicleType.values().length + ")");
            } else {
                AddVehicleResponse addVehicleResponse = executeUserChoice(userChoice);

                if (addVehicleResponse.hasErrors()) {
                    addVehicleResponse.getErrors().forEach(coreError ->
                            System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
                    );
                } else {
                    System.out.println("New vehicle id was: " + addVehicleResponse.getNewVehicle().getId());
                    System.out.println("Your vehicle was added to list.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: You must enter a number!");
        }
    }

    private void printVehicleTypeMenu() {

        System.out.println("""
                Choose vehicle type to add
                1. Passenger Car
                2. Mini Bus
                3. Motorcycle
                4. Car Trailer""");

    }

    private int getUserChoice() {
        return Integer.parseInt(scanner.nextLine().replaceAll("[^0-9]", ""));
    }

    private AddVehicleResponse executeUserChoice(int userChoice) {

        AddVehicleResponse addVehicleResponse = null;

        switch (userChoice) {
            case 1 -> {
                AddVehicleRequest request = createPassengerCarRequest();
                addVehicleResponse = addVehicleService.execute(request);
            }
            case 2 -> {
                AddVehicleRequest request = createMiniBusRequest();
                addVehicleResponse = addVehicleService.execute(request);
            }
            case 3 -> {
                AddVehicleRequest request = createMotorcycleRequest();
                addVehicleResponse = addVehicleService.execute(request);
            }
            case 4 -> {
                AddVehicleRequest request = createCarTrailerRequest();
                addVehicleResponse = addVehicleService.execute(request);
            }
        }
        return addVehicleResponse;
    }

    private AddVehicleRequest createPassengerCarRequest() {

        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year of production (Should be newer than " + (LocalDate.now().getYear() - MAX_ALLOWED_CURRENT_YEAR_BACKWARD_REDUCER) + " year) : ");
        Integer year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter color from " + Colour.getAllEnumValues() + " : ");
        String color = scanner.nextLine();
        System.out.println("Enter rent price per day: ");
        Double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter engine type " + EngineType.getAllEnumValues() + " : ");
        String engineType = scanner.nextLine();
        System.out.println("Enter plate number: ");
        String plateNumber = scanner.nextLine();
        System.out.println("Enter transmission type " + TransmissionType.getAllEnumValues() + " : ");
        String transmissionType = scanner.nextLine();

        System.out.println("Enter passenger amount between " + CAR_MIN_PASSENGER_AMOUNT + " - " + CAR_MAX_PASSENGER_AMOUNT + " : ");
        Integer passengerAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter baggage amount between 0 - " + CAR_MAX_BAGGAGE_AMOUNT + " : ");
        Integer baggageAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter doors amount between " + CAR_MIN_DOORS_AMOUNT + " - " + CAR_MAX_DOORS_AMOUNT + " : ");
        Integer doorsAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter is air conditioning available (true or false) : ");
        String isAirConditioningAvailable = scanner.nextLine();

        return AddVehicleRequest.builder()
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

    private AddVehicleRequest createMiniBusRequest() {

        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year of production (Should be newer than " + (LocalDate.now().getYear() - MAX_ALLOWED_CURRENT_YEAR_BACKWARD_REDUCER) + " year) : ");
        Integer year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter color from " + Colour.getAllEnumValues() + " : ");
        String color = scanner.nextLine();
        System.out.println("Enter rent price per day: ");
        Double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter engine type " + EngineType.getAllEnumValues() + " : ");
        String engineType = scanner.nextLine();
        System.out.println("Enter plate number: ");
        String plateNumber = scanner.nextLine();
        System.out.println("Enter transmission type " + TransmissionType.getAllEnumValues() + " : ");
        String transmissionType = scanner.nextLine();

        System.out.println("Enter passenger amount between " + BUS_MIN_PASSENGER_AMOUNT + " - " + BUS_MAX_PASSENGER_AMOUNT + " : ");
        Integer passengerAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter baggage amount between 0 - " + BUS_MAX_BAGGAGE_AMOUNT + " : ");
        Integer baggageAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter doors amount between " + BUS_MIN_DOORS_AMOUNT + " - " + BUS_MAX_DOORS_AMOUNT + " : ");
        Integer doorsAmount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter is air conditioning available (true or false) : ");
        String isAirConditioningAvailable = scanner.nextLine();

        return AddVehicleRequest.builder()
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

    private AddVehicleRequest createMotorcycleRequest() {

        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year of production (Should be newer than " + (LocalDate.now().getYear() - MAX_ALLOWED_CURRENT_YEAR_BACKWARD_REDUCER) + " year) : ");
        Integer year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter color from " + Colour.getAllEnumValues() + " : ");
        String color = scanner.nextLine();
        System.out.println("Enter rent price per day: ");
        Double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter engine type " + EngineType.getAllEnumValues() + " : ");
        String engineType = scanner.nextLine();
        System.out.println("Enter plate number: ");
        String plateNumber = scanner.nextLine();
        System.out.println("Enter transmission type " + TransmissionType.getAllEnumValues() + " : ");
        String transmissionType = scanner.nextLine();

        System.out.println("Enter passenger amount between " + MOTO_MIN_PASSENGER_AMOUNT + " - " + MOTO_MAX_PASSENGER_AMOUNT + " : ");
        Integer passengerAmount = Integer.parseInt(scanner.nextLine());

        return AddVehicleRequest.builder()
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

    private AddVehicleRequest createCarTrailerRequest() {

        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year of production (Should be newer than " + (LocalDate.now().getYear() - MAX_ALLOWED_CURRENT_YEAR_BACKWARD_REDUCER) + " year) : ");
        Integer year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter color from " + Colour.getAllEnumValues() + " : ");
        String color = scanner.nextLine();
        System.out.println("Enter rent price per day: ");
        Double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter engine type " + EngineType.getAllEnumValues() + " : ");
        String engineType = scanner.nextLine();
        System.out.println("Enter plate number: ");
        String plateNumber = scanner.nextLine();
        System.out.println("Enter transmission type " + TransmissionType.getAllEnumValues() + " : ");
        String transmissionType = scanner.nextLine();

        System.out.println("Enter deck width in cm between (" + TRAIL_MIN_DECK_WIDTH_IN_CM + " - " + TRAIL_MAX_DECK_WIDTH_IN_CM + ") : ");
        Integer deckWidthInCm = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter deck length in cm between (" + TRAIL_MIN_DECK_LENGTH_IN_CM + " - " + TRAIL_MAX_DECK_LENGTH_IN_CM + ") : ");
        Integer deckLengthInCm = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter deck  height in cm between (" + TRAIL_MIN_DECK_HEIGHT_IN_CM + " - " + TRAIL_MAX_DECK_HEIGHT_IN_CM + ") : ");
        Integer deckHeightInCm = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter trailer empty weight in kg between (" + TRAIL_MIN_EMPTY_WEIGHT_IN_KG + " - " + TRAIL_MAX_EMPTY_WEIGHT_IN_KG + ") : ");
        Integer emptyWeightInKg = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter trailer max load weight in kg between (" + TRAIL_MIN_LOAD_WEIGHT_IN_KG + " - " + TRAIL_MAX_LOAD_WEIGHT_IN_KG + ") : ");
        Integer maxLoadWeightInKg = Integer.parseInt(scanner.nextLine());

        return AddVehicleRequest.builder()
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
