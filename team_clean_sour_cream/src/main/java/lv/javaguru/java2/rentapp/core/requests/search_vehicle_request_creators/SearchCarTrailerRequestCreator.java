package lv.javaguru.java2.rentapp.core.requests.search_vehicle_request_creators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchCarTrailerRequestCreator implements SearchVehicleRequestCreator {

    Scanner scanner = new Scanner(System.in);

    @Override
    public SearchVehicleRequest createRequest() {

        SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder = SearchVehicleRequest.builder().vehicleType(VehicleType.CAR_TRAILER);

        boolean addAnotherCriteria = true;

        List<String> criteria = carTrailerSearchCriteriaFields();

        while (addAnotherCriteria) {

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
                    case "Deck width in cm" -> askDeckWidthInCm(searchVehicleRequestBuilder, criteria);
                    case "Deck length in cm" -> askDeckLengthInCm(searchVehicleRequestBuilder, criteria);
                    case "Deck height in cm" -> askDeckHeightInCm(searchVehicleRequestBuilder, criteria);
                    case "Empty weight in kg" -> askEmptyWeightInKg(searchVehicleRequestBuilder, criteria);
                    case "Max load weight in kg" -> askMaxLoadWeightInKg(searchVehicleRequestBuilder, criteria);
                }
            }
            if (userChoice == 2) {
                addAnotherCriteria = false;
            }
        }
        return searchVehicleRequestBuilder.build();
    }

    private void askDeckWidthInCm(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder, List<String> criteria) {
        System.out.println("Enter deck width in cm: ");
        Integer deckWidthInCm = Integer.parseInt(scanner.nextLine());
        searchVehicleRequestBuilder.deckWidthInCm(deckWidthInCm);
        criteria.remove("Deck width in cm");
    }

    private void askDeckLengthInCm(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder, List<String> criteria) {
        System.out.println("Enter deck length in cm: ");
        Integer deckLengthInCm = Integer.parseInt(scanner.nextLine());
        searchVehicleRequestBuilder.deckLengthInCm(deckLengthInCm);
        criteria.remove("Deck length in cm");
    }

    private void askDeckHeightInCm(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder, List<String> criteria) {
        System.out.println("Enter deck height in cm: ");
        Integer deckHeightInCm = Integer.parseInt(scanner.nextLine());
        searchVehicleRequestBuilder.deckHeightInCm(deckHeightInCm);
        criteria.remove("Deck height in cm");
    }

    private void askEmptyWeightInKg(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder, List<String> criteria) {
        System.out.println("Enter empty weight in kg: ");
        Integer deckWidthInCm = Integer.parseInt(scanner.nextLine());
        searchVehicleRequestBuilder.emptyWeightInKg(deckWidthInCm);
        criteria.remove("Empty weight in kg");
    }

    private void askMaxLoadWeightInKg(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder, List<String> criteria) {
        System.out.println("Enter max load in kg: ");
        Integer maxLoadWeightInKg = Integer.parseInt(scanner.nextLine());
        searchVehicleRequestBuilder.maxLoadWeightInKg(maxLoadWeightInKg);
        criteria.remove("Max load weight in kg");
    }

    private List<String> carTrailerSearchCriteriaFields() {
        return new ArrayList<>(
                List.of("Transmission type",
                        "Deck width in cm",
                        "Deck length in cm",
                        "Deck height in cm",
                        "Empty weight in kg",
                        "Max load weight in kg"));
    }
}
