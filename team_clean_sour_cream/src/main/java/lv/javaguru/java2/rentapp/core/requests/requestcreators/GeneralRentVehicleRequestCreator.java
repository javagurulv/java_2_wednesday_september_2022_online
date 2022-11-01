package lv.javaguru.java2.rentapp.core.requests.requestcreators;

import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.requests.Paging;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.requests.requestcreators.search_vehicle_request_creators.SearchVehicleRequestCreator;
import lv.javaguru.java2.rentapp.core.requests.requestcreators.search_vehicle_request_creators.SearchVehicleRequestCreatorMap;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class GeneralRentVehicleRequestCreator {
    @Autowired
    private SearchVehicleRequestCreatorMap searchVehicleRequestCreatorMap;

    public GeneralRentVehicleRequest createVehicleAvailabilityRequest() {
        Scanner scanner = new Scanner(System.in);
        GeneralRentVehicleRequest.GeneralRentVehicleRequestBuilder requestBuilder = GeneralRentVehicleRequest.builder();

        System.out.println("Enter the dates for the period you`d like to rent the vehicle");

        System.out.println("Enter start date (in \"dd/MM/yyyy\" format): ");
        String rentStartDate = scanner.nextLine();
        System.out.println("Enter end date (in \"dd/MM/yyyy\" format): ");
        String rentEndDate = scanner.nextLine();

        printVehicleTypesMenu();
        int vehicleType = Integer.parseInt(scanner.nextLine());

        if ((vehicleType <= 0) || (vehicleType > searchVehicleRequestCreatorMap.getRequestCreatorMapSize())) {
            System.out.println("You must enter an integer that corresponds with a number from program menu (1 - " + searchVehicleRequestCreatorMap.getRequestCreatorMapSize() + ")");
        } else {
            SearchVehicleRequestCreator searchVehicleRequestCreator = searchVehicleRequestCreatorMap.getSearchVehicleRequestCreator(vehicleType);
            searchVehicleRequestCreator.setPagingEnabled(false);
            SearchVehicleRequest searchVehicleRequest = searchVehicleRequestCreatorMap.getSearchVehicleRequestCreator(vehicleType).createRequest();
            searchVehicleRequestCreator.setPagingEnabled(true);
            requestBuilder.searchVehicleRequest(searchVehicleRequest);
        }

        askPaging(requestBuilder);
        return requestBuilder
                .rentStartDate(rentStartDate)
                .rentEndDate(rentEndDate).build();
    }

    public GeneralRentVehicleRequest createRentVehicleRequest(GeneralRentVehicleRequest request, List<Vehicle> availableVehicles) {
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
                .availableVehicles(availableVehicles)
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

    private void printVehicleTypesMenu() {
        System.out.println();
        System.out.println("""
                Choose a type of vehicle you'd like to rent
                1. Passenger Car
                2. Mini Bus
                3. Motorcycle
                4. Car Trailer""");
        System.out.println();
    }

}
