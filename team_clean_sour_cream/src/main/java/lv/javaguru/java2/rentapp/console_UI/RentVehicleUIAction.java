package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.requests.requestcreators.GeneralRentVehicleRequestCreator;
import lv.javaguru.java2.rentapp.core.responses.RentVehicleResponse;
import lv.javaguru.java2.rentapp.core.responses.VehicleAvailabilityResponse;
import lv.javaguru.java2.rentapp.core.services.RentVehicleService;
import lv.javaguru.java2.rentapp.core.services.VehicleAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RentVehicleUIAction implements UIAction {

    @Autowired
    private VehicleAvailabilityService vehicleAvailabilityService;
    @Autowired
    private GeneralRentVehicleRequestCreator requestCreator;
    @Autowired
    private RentVehicleService rentVehicleService;

    @Override
    public void execute() {

        GeneralRentVehicleRequest rentVehicleAvailabilityRequest = requestCreator.createVehicleAvailabilityRequest();
        VehicleAvailabilityResponse vehicleAvailabilityResponse = vehicleAvailabilityService.execute(rentVehicleAvailabilityRequest);
        RentVehicleResponse rentVehicleResponse = null;

        if (vehicleAvailabilityResponse.hasErrors()) {
            vehicleAvailabilityResponse.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else if (vehicleAvailabilityResponse.getVehicles().isEmpty()) {
            System.out.println("No available vehicle found in that range");
        } else if (rentVehicleAvailabilityRequest.getPaging() != null) {
            selectPageMenu(rentVehicleAvailabilityRequest, vehicleAvailabilityResponse);
            GeneralRentVehicleRequest rentVehicleRequest = requestCreator.createRentVehicleRequest(rentVehicleAvailabilityRequest);
            rentVehicleResponse = rentVehicleService.execute(rentVehicleRequest);
        } else {
            System.out.println("Available vehicles found in that range: ");
            vehicleAvailabilityResponse.getVehicles().forEach(System.out::println);
            GeneralRentVehicleRequest rentVehicleRequest = requestCreator.createRentVehicleRequest(rentVehicleAvailabilityRequest);
            rentVehicleResponse = rentVehicleService.execute(rentVehicleRequest);
        }

        if (rentVehicleResponse != null && rentVehicleResponse.hasErrors()) {
            rentVehicleResponse.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else if (rentVehicleResponse != null) {
            System.out.println();
            System.out.println(rentVehicleResponse.getMessage());
        }

    }

    private void selectPageMenu(GeneralRentVehicleRequest request, VehicleAvailabilityResponse response) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available vehicles (Page " + request.getPaging().getPageNumber() + "): ");
        response.getVehicles().forEach(System.out::println);
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
            int userChoice = Integer.parseInt(scanner.nextLine());
            switch (userChoice) {
                case 1 -> {
                    request.getPaging().setPageNumber(++resultPageNumber);
                    response = vehicleAvailabilityService.execute(request);
                    if (response.getVehicles().isEmpty()) {
                        System.out.println("Page " + resultPageNumber + " is empty");
                        request.getPaging().setPageNumber(--resultPageNumber);
                        response = vehicleAvailabilityService.execute(request);
                    }
                    System.out.println("Vehicles found(Page " + request.getPaging().getPageNumber() + "): ");
                    response.getVehicles().forEach(System.out::println);
                }
                case 2 -> {
                    if (resultPageNumber != 1) {
                        request.getPaging().setPageNumber(--resultPageNumber);
                        response = vehicleAvailabilityService.execute(request);
                        System.out.println("Vehicles found(Page " + request.getPaging().getPageNumber() + "): ");
                        response.getVehicles().forEach(System.out::println);
                    } else {
                        System.out.println("You are already viewing the 1st page!");
                    }
                }
                case 3 -> continueSearch = false;

                default -> System.out.println("You must choose one of the provided options (1-3)");
            }
        }
    }

}
