package myApp.consoleUI;

import myApp.core.requests.AddBankAccountRequest;
import myApp.core.responses.AddBankAccountResponse;
import myApp.core.services.AddBankAccountService;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class AddBankAccountUIAction implements UIAction {
    @DIDependency
    private AddBankAccountService service;
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
            if (response.getBankAccount() != null) {
                System.out.println("Bank account has been added");
            } else {
                System.out.println("Bank account has not been added");
            }
        }
    }
}
