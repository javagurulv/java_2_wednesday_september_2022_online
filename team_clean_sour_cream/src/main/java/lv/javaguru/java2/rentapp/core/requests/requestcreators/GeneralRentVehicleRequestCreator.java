package lv.javaguru.java2.rentapp.core.requests.requestcreators;

import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.requests.Paging;

import java.util.Scanner;

public class GeneralRentVehicleRequestCreator {


    public GeneralRentVehicleRequest createVehicleAvailabilityRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the dates for the period you`d like to rent the vehicle");

        System.out.println("Enter start date (in \"dd/MM/yyyy\" format): ");
        String rentStartDate = scanner.nextLine();
        System.out.println("Enter end date (in \"dd/MM/yyyy\" format): ");
        String rentEndDate = scanner.nextLine();

        GeneralRentVehicleRequest.GeneralRentVehicleRequestBuilder requestBuilder = GeneralRentVehicleRequest.builder();
        askPaging(requestBuilder);
        return requestBuilder
                .rentStartDate(rentStartDate)
                .rentEndDate(rentEndDate).build();
    }

    public GeneralRentVehicleRequest createRentVehicleRequest(GeneralRentVehicleRequest request) {
        Scanner scanner = new Scanner(System.in);
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
        String startDate = request.getRentStartDate();
        String endDate = request.getRentEndDate();

        return GeneralRentVehicleRequest.builder()
                .vehicleId(vehicleId)
                .personalId(personalId)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .rentStartDate(startDate)
                .rentEndDate(endDate)
                .build();
    }

    private void askPaging(GeneralRentVehicleRequest.GeneralRentVehicleRequestBuilder requestBuilder) {
        Scanner scanner = new Scanner(System.in);
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
                    System.out.println("Enter size of the page");
                    Integer pageSize = Integer.parseInt(scanner.nextLine());
                    System.out.println();
                    Paging paging = new Paging(1, pageSize);
                    requestBuilder.paging(paging);
                    page = false;

                } else if (userChoice == 2) {
                    page = false;
                } else {
                    System.out.println();
                    System.out.println("You must choose 1 or 2");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: You must enter a number!");

            }
        }
    }

}
