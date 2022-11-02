package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import lv.javaguru.java2.tasksScheduler.requests.GetUsersRequest;
//import lv.javaguru.java2.tasksScheduler.responses.GetAllUsersNamesResponse;
import lv.javaguru.java2.tasksScheduler.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.responses.GetUsersResponse;
//import lv.javaguru.java2.tasksScheduler.services.menu_services.GetAllUsersNamesService;
import lv.javaguru.java2.tasksScheduler.services.menu_services.GetUsersService;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GetUsersNamesUIAction implements UIAction {

    @Autowired
    private GetUsersService getUsersService;

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter Username to filter by if required: ");
        String username = scanner.nextLine();

        Ordering ordering = getOrderingInput();
        Paging paging = getPagingInput();

        do {
            GetUsersRequest request = new GetUsersRequest(username, null, ordering, paging);
            GetUsersResponse response = getUsersService.execute(request, MenuType.START);

            if (response.hasErrors()) {
                response.getErrors().forEach(coreError ->
                        System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
                );
                return false;
            }
            if (response.getUsersNames().isEmpty()) {
                System.out.println("There are no usernames to display.");
                return true;
            }

            System.out.println("--- Usernames list start --- ");
            response.getUsersNames().forEach(System.out::println);
            System.out.println("---  Usernames list end  --- ");

            if (paging == null) {
                break;
            }

            int totalPages = getPagesCount(username, paging.getPageSize());
            System.out.println();
            System.out.println("Total pages count: " + totalPages);
            System.out.println("Enter: page number to show, enter - to show next page or 'q' - to quit): ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                break;
            }
            if (ValueChecking.stringIsEmpty(input)) {
                if (paging.getPageNumber() == totalPages) {
                    System.out.println("The following page doesn't exist");
                } else {
                    paging.setPageNumber(paging.getPageNumber() + 1);
                }
                continue;
            }
            if (!ValueChecking.stringIsInteger(input)) {
                System.out.println("Invalid page number is provided!");
                continue;
            }
            if (Integer.parseInt(input) > totalPages) {
                System.out.println("Provided page number is out of pages range!");
                continue;
            }
            paging.setPageNumber(Integer.parseInt(input));
        } while (true);

        return true;
    }

    private Ordering getOrderingInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter field to order by ('username'): ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter ordering direction ('ASCENDING'/'DESCENDING'): ");
        String orderDirection = scanner.nextLine();
        return (ValueChecking.stringIsEmpty(orderBy) &&
                ValueChecking.stringIsEmpty(orderDirection)) ? null : new Ordering(orderBy, orderDirection);
    }

    private Paging getPagingInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter page number: ");
        String input = scanner.nextLine();
        Integer pageNumber = ValueChecking.stringIsInteger(input) ?
                Integer.parseInt(input) : null;
        System.out.println("Enter page size: ");
        input = scanner.nextLine();
        Integer pageSize = ValueChecking.stringIsInteger(input) ?
                Integer.parseInt(input) : null;
        return (pageNumber == null && pageSize == null) ? null : new Paging(pageNumber, pageSize);
    }

    private Integer getPagesCount(String username, int pageSize) {
        if (pageSize == 0) {
            return 0;
        }

        int result = 0;

        GetUsersRequest request = new GetUsersRequest(username, null, null, null);
        GetUsersResponse response = getUsersService.execute(request, MenuType.START);

        int total = response.getUsers().size();
        result = total / pageSize;

        return (total % pageSize > 0) ? ++result : result;
    }
}
