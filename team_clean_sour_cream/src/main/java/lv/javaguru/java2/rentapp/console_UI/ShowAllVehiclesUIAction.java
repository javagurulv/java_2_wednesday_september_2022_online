package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.requests.ShowAllVehiclesRequest;
import lv.javaguru.java2.rentapp.core.requests.requestcreators.ShowAllVehicleRequestCreator;
import lv.javaguru.java2.rentapp.core.responses.SearchVehicleResponse;
import lv.javaguru.java2.rentapp.core.responses.ShowAllVehiclesResponse;
import lv.javaguru.java2.rentapp.core.services.ShowAllVehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ShowAllVehiclesUIAction implements UIAction {
    @Autowired
    private ShowAllVehiclesService showAllVehiclesService;

    @Override
    public void execute() {

        ShowAllVehiclesRequest request = new ShowAllVehicleRequestCreator().execute();
        ShowAllVehiclesResponse response = showAllVehiclesService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else if (response.getMsg() != null) {
            System.out.println(response.getMsg());
        } else if (request.getPaging() != null) {
            selectPageMenu(request, response);
        } else {
            System.out.println("All vehicles list: ");
            response.getVehicles().forEach(System.out::println);
            System.out.println("end of list.");
        }
    }

    private void selectPageMenu(ShowAllVehiclesRequest request, ShowAllVehiclesResponse response) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Page " + request.getPaging().getPageNumber() + ": ");
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
            System.out.println();
            int userChoice = Integer.parseInt(scanner.nextLine());
            switch (userChoice) {
                case 1 -> {
                    request.getPaging().setPageNumber(++resultPageNumber);
                    response = showAllVehiclesService.execute(request);
                    if (response.getVehicles().isEmpty()) {
                        System.out.println("Page " + resultPageNumber + " is empty");
                        request.getPaging().setPageNumber(--resultPageNumber);
                        response = showAllVehiclesService.execute(request);
                    }
                    System.out.println("Page " + request.getPaging().getPageNumber() + ": ");
                    response.getVehicles().forEach(System.out::println);
                }
                case 2 -> {
                    if (resultPageNumber != 1) {
                        request.getPaging().setPageNumber(--resultPageNumber);
                        response = showAllVehiclesService.execute(request);
                        System.out.println("Page " + request.getPaging().getPageNumber() + ": ");
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
