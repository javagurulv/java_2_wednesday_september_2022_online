package lv.javaguru.java2.rentapp.core.requests.request_creators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchMotorcycleRequestCreator implements SearchVehicleRequestCreator {
    @Override
    public SearchVehicleRequest createRequest() {

        Scanner scanner = new Scanner(System.in);
        SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder = SearchVehicleRequest.builder().vehicleType("Motorcycle");

        boolean addAnotherCriteria = true;

        List<String> criteria = motorcycleSearchCriteriaFields();

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
                    case "Passenger amount" -> askPassengerAmount(searchVehicleRequestBuilder, criteria);
                    case "Transmission type" -> askTransmissionType(searchVehicleRequestBuilder, criteria);
                }
            }
            if (userChoice == 2) {
                addAnotherCriteria = false;
            }
        }
        SearchVehicleRequest request = searchVehicleRequestBuilder.build();
        return request;
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

    private List<String> motorcycleSearchCriteriaFields() {
        return new ArrayList<>(
                List.of("Transmission type",
                        "Passenger amount"));
    }

}
