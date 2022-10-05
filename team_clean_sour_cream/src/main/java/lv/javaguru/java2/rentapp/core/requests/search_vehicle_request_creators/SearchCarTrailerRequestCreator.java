package lv.javaguru.java2.rentapp.core.requests.search_vehicle_request_creators;

import lv.javaguru.java2.rentapp.core.requests.Ordering;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchCarTrailerRequestCreator implements SearchVehicleRequestCreator {

    Scanner scanner = new Scanner(System.in);

    @Override
    public SearchVehicleRequest createRequest() {

        SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder = SearchVehicleRequest.builder().vehicleType("CarTrailer");

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
                    case "Transmission type" -> askTransmissionType(searchVehicleRequestBuilder, criteria);
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

    private void askTransmissionType(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder, List<String> criteria) {
        System.out.println("Enter transmission type (manual, automatic, none): ");
        String transmissionType = scanner.nextLine();
        searchVehicleRequestBuilder.transmissionType(transmissionType);
        criteria.remove("Transmission type");
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
