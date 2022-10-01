package lv.javaguru.java2.rentapp.core.requests.request_creators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchPassengerCarRequestCreator implements SearchVehicleRequestCreator {

    @Override
    public SearchVehicleRequest createRequest() {
        Scanner scanner = new Scanner(System.in);
        SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder = SearchVehicleRequest.builder().vehicleType("PassengerCar");

        boolean addAnotherCriteria = true;

        List<String> criteria = passengerCarSearchCriteriaFields();

        while (addAnotherCriteria) {
            System.out.println("""
                    Add another criteria for search?
                    1. Yes
                    2. No""");
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice == 1) {

                for (int i = 0; i < criteria.size(); i++) {
                    System.out.println(i + 1 + " " + criteria.get(i));
                }

                int criteriaChoice = Integer.parseInt(scanner.nextLine());

                switch (criteria.get(criteriaChoice - 1)) {
                    case "Transmission type" -> {
                        askTransmissionType(searchVehicleRequestBuilder, criteria);
                    }
                    case "Passenger amount" -> {
                        askPassengerAmount(searchVehicleRequestBuilder, criteria);
                    }
                    case "Doors amount" -> {
                        askDoorsAmount(searchVehicleRequestBuilder, criteria);
                    }
                    case "Baggage amount" -> {
                        askBaggageAmount(searchVehicleRequestBuilder, criteria);
                    }
                    case "Conditioner" -> {
                        askConditioner(searchVehicleRequestBuilder, criteria);
                    }
                }
            }
            if (userChoice == 2) {
                addAnotherCriteria = false;
            }
        }
        SearchVehicleRequest request = searchVehicleRequestBuilder.build();
        return request;
    }

    private List<String> passengerCarSearchCriteriaFields() {
        return new ArrayList<>(
                List.of("Transmission type",
                        "Passenger amount",
                        "Doors amount",
                        "Baggage amount",
                        "Conditioner"));
    }

    private void askConditioner(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder, List<String> criteria) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Has conditioner(true or false): ");
        String hasConditioner = scanner.nextLine();
        searchVehicleRequestBuilder.hasConditioner(hasConditioner);
        criteria.remove("Conditioner");
    }

    private void askBaggageAmount(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder, List<String> criteria) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of baggage (0-5): ");
        Integer numberOfBaggage = Integer.parseInt(scanner.nextLine());
        searchVehicleRequestBuilder.baggageAmount(numberOfBaggage);
        criteria.remove("Baggage amount");
    }

    private void askDoorsAmount(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder, List<String> criteria) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of doors (3-5): ");
        Integer numberOfDoors = Integer.parseInt(scanner.nextLine());
        searchVehicleRequestBuilder.doorsAmount(numberOfDoors);
        criteria.remove("Doors amount");
    }

    private void askPassengerAmount(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder, List<String> criteria) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of passengers (1-5): ");
        Integer numberOfPassengers = Integer.parseInt(scanner.nextLine());
        searchVehicleRequestBuilder.passengerAmount(numberOfPassengers);
        criteria.remove("Passenger amount");
    }

    private void askTransmissionType(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder, List<String> criteria) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter transmission type (manual, automatic, none): ");
        String transmissionType = scanner.nextLine();
        searchVehicleRequestBuilder.transmissionType(transmissionType);
        criteria.remove("Transmission type");
    }
}
