package lv.javaguru.java2.rentapp.core.requests.requestcreators.search_vehicle_request_creators;

import lv.javaguru.java2.rentapp.core.requests.Ordering;
import lv.javaguru.java2.rentapp.core.requests.Paging;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static lv.javaguru.java2.rentapp.domain.PassengerCar.*;

@Component
public class SearchPassengerCarRequestCreator implements SearchVehicleRequestCreator {

    Scanner scanner = new Scanner(System.in);

    @Override
    public SearchVehicleRequest createRequest() {

        SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR);

        boolean addAnotherCriteria = true;

        List<String> criteria = passengerCarSearchCriteriaFields();

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

                    if (criteriaChoice >= 1 && criteriaChoice <= criteria.size()) {

                        switch (criteria.get(criteriaChoice - 1)) {
                            case "Transmission type" -> askTransmissionType(searchVehicleRequestBuilder, criteria);
                            case "Passenger amount" -> askPassengerAmount(searchVehicleRequestBuilder, criteria);
                            case "Doors amount" -> askDoorsAmount(searchVehicleRequestBuilder, criteria);
                            case "Baggage amount" -> askBaggageAmount(searchVehicleRequestBuilder, criteria);
                            case "Conditioner" -> askConditioner(searchVehicleRequestBuilder, criteria);
                        }
                    } else {
                        System.out.println("You must enter a number from program menu (1 - " + criteria.size() + ")");
                    }
                } else if (userChoice == 2) {
                    addAnotherCriteria = false;
                } else {
                    System.out.println("You must enter a number from program menu (1 - 2)");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: You must enter a number!");
            }
        }

        askOrdering(searchVehicleRequestBuilder);
        askPaging(searchVehicleRequestBuilder);

        return searchVehicleRequestBuilder.build();
    }

    private List<String> passengerCarSearchCriteriaFields() {
        return new ArrayList<>(
                List.of("Transmission type",
                        "Passenger amount",
                        "Doors amount",
                        "Baggage amount",
                        "Conditioner"));
    }

    private void askPaging(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder) {
        boolean page = true;

        while (page) {
            try {
                System.out.println();
                System.out.println("""
                        Do you wish to split the result of the search into pages?
                        1. Yes
                        2. No""");
                System.out.println();

                int userChoice = Integer.parseInt(scanner.nextLine());
                if (userChoice == 1) {
                    System.out.println();
                    System.out.println("Enter a size of the page");
                    Integer pageSize = Integer.parseInt(scanner.nextLine());
                    System.out.println();
                    Paging paging = new Paging(1, pageSize);
                    searchVehicleRequestBuilder.paging(paging);
                    page = false;

                } else if (userChoice == 2) {
                    page = false;
                } else {
                    System.out.println();
                    System.out.println("You must choose 1 or 2");
                    System.out.println();
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: You must enter a number!");

            }
        }
    }

    private void askOrdering(SearchVehicleRequest.SearchVehicleRequestBuilder searchVehicleRequestBuilder) {
        boolean sort = true;

        while (sort) {
            try {
                System.out.println();
                System.out.println("""
                        Do you wish to sort the result?
                        1. Yes
                        2. No""");
                System.out.println();

                int userChoice = Integer.parseInt(scanner.nextLine());

                if (userChoice == 1) {

                    System.out.println("Enter orderBy (price||year): ");
                    String orderBy = scanner.nextLine().replaceAll("[^a-zA-Z]", "");
                    System.out.println("Enter orderDirection  - \"ASC\" (ascending) || \"DESC\" (descending): ");
                    String orderDirection = scanner.nextLine().replaceAll("[^a-zA-Z]", "");
                    Ordering ordering = new Ordering(orderBy, orderDirection);
                    searchVehicleRequestBuilder.ordering(ordering);
                    sort = false;
                } else if (userChoice == 2) {
                    sort = false;
                } else {
                    System.out.println("You must choose 1 or 2");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: You must enter a number!");
            }
        }
    }

    private void askTransmissionType(SearchVehicleRequest.SearchVehicleRequestBuilder
                                             searchVehicleRequestBuilder, List<String> criteria) {
        System.out.println("Enter transmission type " + TransmissionType.getAllEnumValues() + " : ");
        String transmissionType = scanner.nextLine();
        searchVehicleRequestBuilder.transmissionType(transmissionType);
        criteria.remove("Transmission type");
    }

    private void askPassengerAmount(SearchVehicleRequest.SearchVehicleRequestBuilder
                                            searchVehicleRequestBuilder, List<String> criteria) {
        System.out.println("Enter passenger amount between " + CAR_MIN_PASSENGER_AMOUNT + " - " + CAR_MAX_PASSENGER_AMOUNT + " : ");
        Integer numberOfPassengers = Integer.parseInt(scanner.nextLine());
        searchVehicleRequestBuilder.passengerAmount(numberOfPassengers);
        criteria.remove("Passenger amount");
    }

    private void askDoorsAmount(SearchVehicleRequest.SearchVehicleRequestBuilder
                                        searchVehicleRequestBuilder, List<String> criteria) {
        System.out.println("Enter doors amount between " + CAR_MIN_DOORS_AMOUNT + " - " + CAR_MAX_DOORS_AMOUNT + " : ");
        Integer numberOfDoors = Integer.parseInt(scanner.nextLine());
        searchVehicleRequestBuilder.doorsAmount(numberOfDoors);
        criteria.remove("Doors amount");
    }

    private void askBaggageAmount(SearchVehicleRequest.SearchVehicleRequestBuilder
                                          searchVehicleRequestBuilder, List<String> criteria) {
        System.out.println("Enter baggage amount between 0 - " + CAR_MAX_BAGGAGE_AMOUNT + " : ");
        Integer numberOfBaggage = Integer.parseInt(scanner.nextLine());
        searchVehicleRequestBuilder.baggageAmount(numberOfBaggage);
        criteria.remove("Baggage amount");
    }

    private void askConditioner(SearchVehicleRequest.SearchVehicleRequestBuilder
                                        searchVehicleRequestBuilder, List<String> criteria) {
        System.out.println("Has conditioner(true or false): ");
        String hasConditioner = scanner.nextLine();
        searchVehicleRequestBuilder.hasConditioner(hasConditioner);
        criteria.remove("Conditioner");
    }
}