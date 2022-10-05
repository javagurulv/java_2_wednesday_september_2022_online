package lv.javaguru.java2.rentapp.core.requests.search_vehicle_request_creators;

import lv.javaguru.java2.rentapp.core.requests.Ordering;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchMotorcycleRequestCreator implements SearchVehicleRequestCreator {

    Scanner scanner = new Scanner(System.in);

    @Override
    public SearchVehicleRequest createRequest() {

        SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder = SearchVehicleRequest.builder().vehicleType(VehicleType.MOTORCYCLE);

        boolean addAnotherCriteria = true;

        List<String> criteria = motorcycleSearchCriteriaFields();

        while (addAnotherCriteria) {
            try {
                System.out.println();
                System.out.println("""
                        Add another criteria for search?
                        1. Yes
                        2. No""");
                System.out.println();

                int userChoice = Integer.parseInt(scanner.nextLine());

                if (userChoice == 1) {

                    for (int i = 0; i < criteria.size(); i++) {
                        System.out.println(i + 1 + " " + criteria.get(i));
                    }

                    int criteriaChoice = Integer.parseInt(scanner.nextLine());

                    switch (criteria.get(criteriaChoice - 1)) {
                        case "Passenger amount" -> askPassengerAmount(searchVehicleRequestBuilder, criteria);
                        case "Transmission type" -> askTransmissionType(searchVehicleRequestBuilder, criteria);
                    }
                }
                if (userChoice == 2) {
                    addAnotherCriteria = false;
                } else {
                    System.out.println("You must enter an integer that corresponds with a number from program menu (1 - 2)");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: You must enter a number!");
            }
        }

        askOrdering(searchVehicleRequestBuilder);

        return searchVehicleRequestBuilder.build();
    }


    private void askOrdering(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder) {
        boolean sort = true;
        while (sort) {
            System.out.println("""
                Do you wish to sort the result?
                1. Yes
                2. No""");
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice == 1) {
                System.out.println("Enter orderBy (price||year): ");
                String orderBy = scanner.nextLine().replaceAll("[^a-zA-Z]", "");
                System.out.println("Enter orderDirection (ASCENDING||DESCENDING): ");
                String orderDirection = scanner.nextLine().replaceAll("[^a-zA-Z]", "");
                Ordering ordering = new Ordering(orderBy, orderDirection);
                searchVehicleRequestBuilder.ordering(ordering);
                sort = false;
            } else if (userChoice == 2) {
                sort = false;
            } else {
                System.out.println("You must choose 1 or 2");
            }
        }
    }

    private void askPassengerAmount(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder, List<String> criteria) {
        System.out.println("Enter number of passengers (1-5): ");
        Integer numberOfPassengers = Integer.parseInt(scanner.nextLine());
        searchVehicleRequestBuilder.passengerAmount(numberOfPassengers);
        criteria.remove("Passenger amount");
    }

    private void askTransmissionType(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder, List<String> criteria) {
        System.out.println("Enter transmission type (manual, automatic, none): ");
        String transmissionType = scanner.nextLine();
        searchVehicleRequestBuilder.transmissionType(transmissionType);
        criteria.remove("Transmission type");
    }

    private List<String> motorcycleSearchCriteriaFields() {
        return new ArrayList<>(
                List.of("Transmission type",
                        "Passenger amount"));
    }
}
