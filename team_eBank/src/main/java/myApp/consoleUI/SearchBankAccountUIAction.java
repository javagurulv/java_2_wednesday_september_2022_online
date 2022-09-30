package myApp.consoleUI;

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
        SearchBankAccountRequest request = new SearchBankAccountRequest(name, surname, personalCode);
        SearchBankAccountResponse response = service.execute(request);

        if (response.hasErrors()) {
            System.out.println(response.getErrors());
        } else {
            System.out.println(response.getBankAccounts());
        }
    }
}
