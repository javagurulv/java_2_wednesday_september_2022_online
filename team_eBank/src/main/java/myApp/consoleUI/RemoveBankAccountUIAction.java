package myApp.consoleUI;

import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.responses.RemoveBankAccountResponse;
import myApp.core.services.RemoveBankAccountService;
import myApp.core.database.DataBase;

import java.util.Scanner;

public class RemoveBankAccountUIAction implements UIAction {

    private RemoveBankAccountService service;

    public RemoveBankAccountUIAction(RemoveBankAccountService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account id: ");
        Long id = Long.parseLong(scanner.nextLine());
        RemoveBankAccountRequest request = new RemoveBankAccountRequest(id);
        RemoveBankAccountResponse response = service.execute(request);
        System.out.println("Bank account has been deleted");
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error " + coreError.getField()
                    + " " + coreError.getMessage()));
        } else if (response.isDeleted()){
            System.out.println("Account has been deleted");
        } else {
            System.out.println("Account has not been deleted");
        }
    }
}
