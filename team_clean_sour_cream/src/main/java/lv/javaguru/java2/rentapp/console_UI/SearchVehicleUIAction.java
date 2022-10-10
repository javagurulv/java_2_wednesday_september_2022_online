package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.requests.search_vehicle_request_creators.*;
import lv.javaguru.java2.rentapp.core.responses.SearchVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.SearchVehicleService;

import java.util.*;

public class SearchVehicleUIAction implements UIAction {

    private SearchVehicleService searchVehicleService;
    private SearchVehicleRequestCreatorMap requestCreatorMap = new SearchVehicleRequestCreatorMap();

    public SearchVehicleUIAction(SearchVehicleService searchVehicleService) {
        this.searchVehicleService = searchVehicleService;
    }

    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Start searching by vehicle type first");

        printVehicleTypesMenu();

        try {
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
                    System.out.println("Vehicles found(Page " + request.getPaging().getPageNumber() + "): ");
                    response.getVehicleList().forEach(System.out::println);
                    int resultPageNumber = 1;
                    boolean endSearch = false;
                    while (endSearch) {

                        System.out.println();
                        System.out.println("""
                                Choose an option:
                                1. Show next page
                                2. Show previous page
                                3. End search
                                """);
                        System.out.println();
                        Integer userChoice = Integer.parseInt(scanner.nextLine());
                        switch (userChoice) {
                            case 1:
                                System.out.println();
                            case 2:
                            case 3:
                            default:
                        }

                    }
                } else {
                    System.out.println("Vehicles found by your criteria: ");
                    response.getVehicleList().forEach(System.out::println);
                }


            }
        } catch (NumberFormatException e) {
            System.out.println("Error: You must enter a number!");
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
