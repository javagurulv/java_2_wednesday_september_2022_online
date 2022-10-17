package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.RentVehicleResponse;
import lv.javaguru.java2.rentapp.core.responses.VehicleAvailabilityResponse;
import lv.javaguru.java2.rentapp.core.services.RentVehicleService;
import lv.javaguru.java2.rentapp.core.services.VehicleAvailabilityService;

import java.util.Scanner;

public class RentVehicleUIAction implements UIAction {

    private VehicleAvailabilityService vehicleAvailabilityService;

    private RentVehicleService rentVehicleService;

    Scanner scanner = new Scanner(System.in);

    public RentVehicleUIAction(VehicleAvailabilityService vehicleAvailabilityService, RentVehicleService rentVehicleService) {
        this.vehicleAvailabilityService = vehicleAvailabilityService;
        this.rentVehicleService = rentVehicleService;
    }

    @Override
    public void execute() {

        GeneralRentVehicleRequest.GeneralRentVehicleRequestBuilder generalRentVehicleRequestBuilder = createVehicleAvailabilityRequestBuilder();

        GeneralRentVehicleRequest rentVehicleAvailabilityRequest = generalRentVehicleRequestBuilder.build();
        VehicleAvailabilityResponse vehicleAvailabilityResponse = vehicleAvailabilityService.execute(rentVehicleAvailabilityRequest);

        if (vehicleAvailabilityResponse.hasErrors()) {
            vehicleAvailabilityResponse.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else if (vehicleAvailabilityResponse.getVehicles().isEmpty()) {
            System.out.println("No vehicle available`ve been found in that range");
        } else {
            System.out.println("Available vehicles`ve been found in that range: ");
            vehicleAvailabilityResponse.getVehicles().forEach(System.out::println);

            GeneralRentVehicleRequest rentVehicleRequest = createRentVehicleRequest(generalRentVehicleRequestBuilder);
            RentVehicleResponse rentVehicleResponse = rentVehicleService.execute(rentVehicleRequest);

            if (rentVehicleResponse.hasErrors()) {
                rentVehicleResponse.getErrors().forEach(coreError ->
                        System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
                );
            } else {
                System.out.println();
                System.out.println(rentVehicleResponse.getMessage());
            }
        }

    }

    private GeneralRentVehicleRequest.GeneralRentVehicleRequestBuilder createVehicleAvailabilityRequestBuilder() {

        System.out.println("Enter the dates for the period you`d like to rent the vehicle");

        System.out.println("Enter start date (in \"dd/MM/yyyy\" format): ");
        String rentStartDate = scanner.nextLine();
        System.out.println("Enter end date (in \"dd/MM/yyyy\" format): ");
        String rentEndDate = scanner.nextLine();

        return GeneralRentVehicleRequest.builder()
                .rentStartDate(rentStartDate)
                .rentEndDate(rentEndDate);
    }

    private GeneralRentVehicleRequest createRentVehicleRequest(GeneralRentVehicleRequest.GeneralRentVehicleRequestBuilder
                                                                       generalRentVehicleRequestBuilder) {
        System.out.println("Please enter ID of the vehicle you want to rent: ");
        Long vehicleId = Long.parseLong(scanner.nextLine());
        System.out.println("Please enter your personal ID (in format \"______-_____\": ");
        String personalId = scanner.nextLine();
        System.out.println("Please enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Please enter your phone number");
        String phoneNumber = scanner.nextLine();

        return generalRentVehicleRequestBuilder
                .vehicleId(vehicleId)
                .personalId(personalId)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
