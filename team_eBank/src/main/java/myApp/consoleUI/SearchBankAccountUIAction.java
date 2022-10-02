package myApp.consoleUI;
import myApp.core.requests.Ordering;
import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.SearchBankAccountResponse;
import myApp.core.services.SearchBankAccountService;

import java.util.Scanner;

public class SearchBankAccountUIAction implements UIAction {

    private SearchBankAccountService service;

    public SearchBankAccountUIAction(SearchBankAccountService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter surname: ");
        String surname = scanner.nextLine();
        System.out.println("Enter personal code: ");
        String personalCode = scanner.nextLine();
        System.out.println("Order by 1)name | 2)surname | 3)personal code");
        System.out.println("Enter number: ");
        String orderBy = userAnswerOrderBy(scanner.nextLine());
        System.out.println("Enter order direction 1)ASCENDING | 2)DESCENDING: ");
        System.out.println("Enter number: ");
        String orderDirection = userAnswerOrderDirection(scanner.nextLine());
        Ordering order = new Ordering(orderBy, orderDirection);
        SearchBankAccountRequest request = new SearchBankAccountRequest(name, surname, personalCode, order);
        SearchBankAccountResponse response = service.execute(request);

        if (response.hasErrors()) {
            System.out.println(response.getErrors());
        } else {
            response.getBankAccounts().forEach(System.out::println);
        }
    }
    private String userAnswerOrderBy(String answer) {
        String result = "";
        switch (answer) {
            case "1" -> result = "name";
            case "2" -> result = "surname";
            case "3" -> result = "personal code";
        }
        return result;
    }

    private String userAnswerOrderDirection(String answer) {
        String result = "";
        switch (answer) {
            case "1" -> result = "ASCENDING";
            case "2" -> result = "DESCENDING";
        }
        return result;
    }
}
