package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.requests.requestcreators.search_vehicle_request_creators.*;
import lv.javaguru.java2.rentapp.core.responses.SearchVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.SearchVehicleService;

import java.util.*;

public class SearchVehicleUIAction implements UIAction {

    private SearchVehicleService searchVehicleService;
    private SearchVehicleRequestCreatorMap requestCreatorMap;

    public SearchVehicleUIAction(SearchVehicleService searchVehicleService, SearchVehicleRequestCreatorMap requestCreatorMap) {
        this.searchVehicleService = searchVehicleService;
        this.requestCreatorMap = requestCreatorMap;
    }

    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Start searching by vehicle type first");

        printVehicleTypesMenu();

        int vehicleTypeChoice = Integer.parseInt(scanner.nextLine());

        if ((vehicleTypeChoice <= 0) || (vehicleTypeChoice > requestCreatorMap.getRequestCreatorMapSize())) {
            System.out.println("You must enter an integer that corresponds with a number from program menu (1 - " + requestCreatorMap.getRequestCreatorMapSize() + ")");
        } else {

            SearchVehicleRequest request = requestCreatorMap.getSearchVehicleRequestCreator(vehicleTypeChoice).createRequest();
            SearchVehicleResponse response = searchVehicleService.execute(request);

            if (response.hasErrors()) {
                response.getErrors().forEach(coreError ->
                        System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
            } else if (response.getVehicleList().isEmpty()) {
                System.out.println("No vehicle found that matches your criteria");
            } else if (request.getPaging() != null) {
                selectPageMenu(request, response);
            } else {
                System.out.println("Vehicles found by your criteria: ");
                response.getVehicleList().forEach(System.out::println);
            }
        }
    }

    private void selectPageMenu(SearchVehicleRequest request, SearchVehicleResponse response) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vehicles found(Page " + request.getPaging().getPageNumber() + "): ");
        response.getVehicleList().forEach(System.out::println);
        int resultPageNumber = request.getPaging().getPageNumber();
        boolean continueSearch = true;

        while (continueSearch) {

            System.out.println();
            System.out.println("""
                    Choose an option:
                    1. Show next page
                    2. Show previous page
                    3. End search
                    """);
            System.out.println();
            int userChoice = Integer.parseInt(scanner.nextLine());
            switch (userChoice) {
                case 1 -> {
                    request.getPaging().setPageNumber(++resultPageNumber);
                    response = searchVehicleService.execute(request);
                    if (response.getVehicleList().isEmpty()) {
                        System.out.println("Page " + resultPageNumber + " is empty");
                        request.getPaging().setPageNumber(--resultPageNumber);
                        response = searchVehicleService.execute(request);
                    }
                    System.out.println("Vehicles found(Page " + request.getPaging().getPageNumber() + "): ");
                    response.getVehicleList().forEach(System.out::println);
                }
                case 2 -> {
                    if (resultPageNumber != 1) {
                        request.getPaging().setPageNumber(--resultPageNumber);
                        response = searchVehicleService.execute(request);
                        System.out.println("Vehicles found(Page " + request.getPaging().getPageNumber() + "): ");
                        response.getVehicleList().forEach(System.out::println);
                    } else {
                        System.out.println("You are already viewing the 1st page!");
                    }
                }
                case 3 -> continueSearch = false;

                default -> System.out.println("You must choose one of the provided options (1-3)");
            }
        }
    }

    private void printVehicleTypesMenu() {
        System.out.println();
        System.out.println("""
                Choose vehicle type:
                1. Passenger Car
                2. Mini Bus
                3. Motorcycle
                4. Car Trailer""");
        System.out.println();
    }
}
