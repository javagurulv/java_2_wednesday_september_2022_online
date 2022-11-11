package lv.javaguru.java2.rentapp.core.requests.requestcreators;

import lv.javaguru.java2.rentapp.core.requests.Paging;
import lv.javaguru.java2.rentapp.core.requests.ShowAllVehiclesRequest;

import java.util.Scanner;

public class ShowAllVehicleRequestCreator {

    public ShowAllVehiclesRequest execute() {
        ShowAllVehiclesRequest.ShowAllVehiclesRequestBuilder requestBuilder = ShowAllVehiclesRequest.builder();
        askPaging(requestBuilder);
        return requestBuilder.build();
    }

    private void askPaging(ShowAllVehiclesRequest.ShowAllVehiclesRequestBuilder requestBuilder) {
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
