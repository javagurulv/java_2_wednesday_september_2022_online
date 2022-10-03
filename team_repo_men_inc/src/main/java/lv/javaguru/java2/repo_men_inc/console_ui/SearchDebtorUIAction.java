package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.core.requests.*;
import lv.javaguru.java2.repo_men_inc.core.responses.SearchDebtorResponse;
import lv.javaguru.java2.repo_men_inc.services.SearchDebtorService;

import java.util.Scanner;

public class SearchDebtorUIAction implements UIAction {

    private final SearchDebtorService searchDebtorService;

    public SearchDebtorUIAction(SearchDebtorService searchDebtorService) {
        this.searchDebtorService = searchDebtorService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Debtor Name: ");
        String debtorName = scanner.nextLine();
        System.out.println("Enter Item Name: ");
        String itemName = scanner.nextLine();

        System.out.println("Enter orderBy (NAME||LIST_ITEM_SIZE): ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter orderDirection (ASC||DESC): ");
        String orderDirection = scanner.nextLine();

        OrderingType orderingType;
        try {
            if (orderBy.isEmpty()) {
                orderingType = OrderingType.EMPTY;
            } else {
                orderingType = OrderingType.valueOf(orderBy);
            }
        } catch (Exception exception) {
            orderingType = OrderingType.INVALID;
        }

        OrderingDirection orderingDirection;
        try {
            if (orderDirection.isEmpty()) {
                orderingDirection = OrderingDirection.EMPTY;
            } else {
                orderingDirection = OrderingDirection.valueOf(orderDirection);
            }
        } catch (Exception exception) {
            orderingDirection = OrderingDirection.INVALID;
        }

        Ordering ordering = !orderBy.isEmpty() || !orderDirection.isEmpty()
                ? new Ordering(orderingType, orderingDirection)
                : null;


        Integer pageNumberInt = null;
        Integer pageSizeInt = null;
        System.out.println("Enter pageNumber: ");
        String pageNumberStr = scanner.nextLine();
        System.out.println("Enter pageSize: ");
        String pageSizeStr = scanner.nextLine();

        if (!pageNumberStr.isEmpty()) {
            pageNumberInt = Integer.parseInt(pageNumberStr);
        }

        if (!pageSizeStr.isEmpty()) {
            pageSizeInt = Integer.parseInt(pageSizeStr);
        }

        Paging paging = !pageNumberStr.isEmpty() || !pageSizeStr.isEmpty()
                ? new Paging(pageNumberInt, pageSizeInt)
                : null;

        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest(debtorName, itemName, ordering, paging);
        SearchDebtorResponse searchDebtorResponse = searchDebtorService.execute(searchDebtorRequest);

        if (searchDebtorResponse.hasErrors()) {
            System.out.println("===================== errors =====================");
            searchDebtorResponse.getErrors().forEach(coreError -> System.out.println(coreError.getField() + ": " + coreError.getMessage()));
            System.out.println("==================================================");
        } else {
            System.out.println("================= search results =================");
            searchDebtorResponse.getDebtors().forEach(System.out::println);
            System.out.println("=============== search results end ===============");
        }
    }
}
