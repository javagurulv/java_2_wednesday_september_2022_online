package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.requests.requestcreators.GeneralRentVehicleRequestCreator;
import lv.javaguru.java2.rentapp.core.responses.RentVehicleResponse;
import lv.javaguru.java2.rentapp.core.responses.SearchVehicleResponse;
import lv.javaguru.java2.rentapp.core.responses.VehicleAvailabilityResponse;
import lv.javaguru.java2.rentapp.core.services.RentVehicleService;
import lv.javaguru.java2.rentapp.core.services.SearchVehicleService;
import lv.javaguru.java2.rentapp.core.services.VehicleAvailabilityService;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class RentVehicleUIAction implements UIAction {

    @Autowired
    private VehicleAvailabilityService vehicleAvailabilityService;
    @Autowired
    private GeneralRentVehicleRequestCreator requestCreator;
    @Autowired
    private RentVehicleService rentVehicleService;
    @Autowired
    private SearchVehicleService searchVehicleService;

    @Override
    public void execute() {

        GeneralRentVehicleRequest rentVehicleAvailabilityRequest = requestCreator.createVehicleAvailabilityRequest();

        SearchVehicleResponse searchVehicleResponse = searchVehicleService.execute(rentVehicleAvailabilityRequest.getSearchVehicleRequest());
        List<Vehicle> foundVehicles = searchVehicleResponse.getVehicleList();

        if (searchVehicleResponse.hasErrors()) {
            searchVehicleResponse.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
            return;
        }

        VehicleAvailabilityResponse vehicleAvailabilityResponse = vehicleAvailabilityService.execute(rentVehicleAvailabilityRequest, foundVehicles);
        RentVehicleResponse rentVehicleResponse = null;

        if (vehicleAvailabilityResponse.hasErrors()) {
            vehicleAvailabilityResponse.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else if (vehicleAvailabilityResponse.getVehicles().isEmpty()) {
            System.out.println("No available vehicle found in that range");
        } else if (rentVehicleAvailabilityRequest.getSearchVehicleRequest().getPaging() != null) {
            selectPageMenu(rentVehicleAvailabilityRequest, foundVehicles);

            GeneralRentVehicleRequest rentVehicleRequest = requestCreator.createRentVehicleRequest(rentVehicleAvailabilityRequest, vehicleAvailabilityResponse.getVehicles());
            rentVehicleResponse = rentVehicleService.execute(rentVehicleRequest);
        } else {
            System.out.println("Available vehicles found in that range: ");
            vehicleAvailabilityResponse.getVehicles().forEach(System.out::println);

            GeneralRentVehicleRequest rentVehicleRequest = requestCreator.createRentVehicleRequest(rentVehicleAvailabilityRequest, vehicleAvailabilityResponse.getVehicles());
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

    private void selectPageMenu(GeneralRentVehicleRequest request, List<Vehicle> foundVehicles) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available vehicles (Page " + request.getPaging().getPageNumber() + "): ");
        foundVehicles.forEach(System.out::println);
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
                    List<Vehicle> availableVehicles = searchVehicleService.execute(request.getSearchVehicleRequest()).getVehicleList();
                    VehicleAvailabilityResponse response = vehicleAvailabilityService.execute(request, availableVehicles);
                    if (response.getVehicles().isEmpty()) {
                        System.out.println("Page " + resultPageNumber + " is empty");
                        System.out.println();
                        request.getPaging().setPageNumber(--resultPageNumber);
                        availableVehicles = searchVehicleService.execute(request.getSearchVehicleRequest()).getVehicleList();
                        response = vehicleAvailabilityService.execute(request, availableVehicles);
                    }
                    System.out.println("Vehicles found(Page " + request.getPaging().getPageNumber() + "): ");
                    response.getVehicles().forEach(System.out::println);
                }
                case 2 -> {
                    if (resultPageNumber != 1) {
                        request.getPaging().setPageNumber(--resultPageNumber);
                        List<Vehicle> availableVehicles = searchVehicleService.execute(request.getSearchVehicleRequest()).getVehicleList();
                        VehicleAvailabilityResponse response = vehicleAvailabilityService.execute(request, availableVehicles);
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
