package myApp.consoleUI;

import myApp.core.requests.AddBankAccountRequest;
import myApp.core.responses.AddBankAccountResponse;
import myApp.core.services.AddBankAccountService;
import myApp.core.database.DataBase;

import java.util.Scanner;

public class AddBankAccountUIAction implements UIAction {

    private AddBankAccountService service;
    public AddBankAccountUIAction(AddBankAccountService service) {
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
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        AddBankAccountRequest request = new AddBankAccountRequest(name, surname, personalCode, password);
        AddBankAccountResponse response = service.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: "
                    + coreError.getField() + " " + coreError.getMessage()));
        } else {
            System.out.println("Bank account has been added");
        }
    }
}
