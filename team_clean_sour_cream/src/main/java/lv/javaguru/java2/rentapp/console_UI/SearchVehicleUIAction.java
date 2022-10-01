package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.requests.request_creators.*;
import lv.javaguru.java2.rentapp.core.responses.SearchVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.SearchVehicleService;

import java.util.*;

public class SearchVehicleUIAction implements UIAction {

    private SearchVehicleService searchVehicleService;
    private Map<Integer, SearchVehicleRequestCreator> requestCreatorMap = new HashMap<>();

    public SearchVehicleUIAction(SearchVehicleService searchVehicleService) {
        this.searchVehicleService = searchVehicleService;
        requestCreatorMap.put(1, new SearchPassengerCarRequestCreator());
        requestCreatorMap.put(2, new SearchMiniBusRequestCreator());
        requestCreatorMap.put(3, new SearchMotorcycleRequestCreator());
        requestCreatorMap.put(4, new SearchCarTrailerRequestCreator());
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Start searching by vehicle type first");

        printVehicleTypesMenu();

        int vehicleTypeChoice = Integer.parseInt(scanner.nextLine());

        SearchVehicleRequest request = requestCreatorMap.get(vehicleTypeChoice).createRequest();
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


    private void printVehicleTypesMenu() {
        System.out.println();
        System.out.println("""
                Chose vehicle type:
                1. Passenger Car
                2. Mini Bus"
                3. Motorcycle"
                4. Car Trailer""");
        System.out.println();
    }


}