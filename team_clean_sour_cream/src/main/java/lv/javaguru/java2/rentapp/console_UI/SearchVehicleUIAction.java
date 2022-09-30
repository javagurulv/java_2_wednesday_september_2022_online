package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.SearchVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.SearchVehicleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchVehicleUIAction implements UIAction {

    private SearchVehicleService searchVehicleService;

    public SearchVehicleUIAction(SearchVehicleService searchVehicleService) {
        this.searchVehicleService = searchVehicleService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Start searching by vehicle type first");

        printVehicleTypesMenu();

        int vehicleTypeChoice = Integer.parseInt(scanner.nextLine());

        switch (vehicleTypeChoice) {

            case 1 -> {

                SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder = SearchVehicleRequest.builder().vehicleType("PassengerCar");

                boolean addAnotherCriteria = true;
                List<String> criteria = searchCriteriaAvailableFields();
//                String passengerAmount = "Passenger amount";
//                String doorsAmount = "Doors amount";
//                String baggageAmount = "Baggage amount";
//                String transmission = "Transmission type";
//                String conditioner = "Conditioner";
//                criteria.add(passengerAmount);
//                criteria.add(doorsAmount);
//                criteria.add(baggageAmount);
//                criteria.add(transmission);
//                criteria.add(conditioner);
                while (addAnotherCriteria) {
                    System.out.println("""
                            Add another criteria for search?
                            1. Yes");
                            2. No""");
                    int userChoice = Integer.parseInt(scanner.nextLine());
                    if (userChoice == 1) {

                        for (int i = 0; i < criteria.size(); i++) {
                            System.out.println(i + 1 + " " + criteria.get(i));
                        }

                        int criteriaChoice = Integer.parseInt(scanner.nextLine());

                        switch (criteria.get(criteriaChoice - 1)) {
                            case "Transmission type" -> {
                                System.out.println("Enter transmission type (manual, automatic, none): ");
                                String transmissionType = scanner.nextLine();
                                searchVehicleRequestBuilder.transmissionType(transmissionType);
                                criteria.remove(criteriaChoice - 1);
                            }
                            case "Passenger amount" -> {
                                System.out.println("Enter number of passengers (1-5): ");
                                Integer numberOfPassengers = Integer.parseInt(scanner.nextLine());
                                searchVehicleRequestBuilder.passengerAmount(numberOfPassengers);
                                criteria.remove(criteriaChoice - 1);
                            }
                            case "Doors amount" -> {
                                System.out.println("Enter number of doors (3-5): ");
                                Integer numberOfDoors = Integer.parseInt(scanner.nextLine());
                                searchVehicleRequestBuilder.doorsAmount(numberOfDoors);
                                criteria.remove(criteriaChoice - 1);
                            }
                            case "Baggage amount" -> {
                                System.out.println("Enter number of baggage (0-5): ");
                                Integer numberOfBaggage = Integer.parseInt(scanner.nextLine());
                                searchVehicleRequestBuilder.baggageAmount(numberOfBaggage);
                                criteria.remove(criteriaChoice - 1);
                            }
                            case "Conditioner" -> {
                                System.out.println("Has conditioner(true or false): ");
                                String hasConditioner = scanner.nextLine();
                                searchVehicleRequestBuilder.hasConditioner(hasConditioner);
                                criteria.remove(criteriaChoice - 1);
                            }
                        }
                    }
                    if (userChoice == 2) {
                        addAnotherCriteria = false;
                    }
                }
                SearchVehicleRequest request = searchVehicleRequestBuilder.build();
                SearchVehicleResponse response = searchVehicleService.execute(request);

                if (response.hasErrors()) {
                    response.getErrors().forEach(coreError ->
                            System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
                    );
                } else {
                    System.out.println("Vehicles found by your criteria: ");
                    response.getVehicleList().forEach(System.out::println);
                }
            }
            case 2 -> {

            }
            case 3 -> {

            }
            case 4 -> {

            }
        }

    }

    private void printVehicleTypesMenu() {
        System.out.println("""
                Chose vehicle type:
                1. Passenger Car
                2. Mini Bus"
                3. Motorcycle"
                4. Car Trailer""");
        System.out.println();
    }

    private List<String> searchCriteriaAvailableFields() {
        return new ArrayList<>(
                List.of("Transmission type",
                        "Passenger amount",
                        "Doors amount",
                        "Baggage amount",
                        "Conditioner"));
    }

}
