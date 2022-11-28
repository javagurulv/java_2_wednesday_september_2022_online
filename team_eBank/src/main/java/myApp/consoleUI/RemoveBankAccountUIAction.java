package myApp.consoleUI;

import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.responses.RemoveBankAccountResponse;
import myApp.core.services.RemoveBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;
//@Component
public class RemoveBankAccountUIAction implements UIAction {

    @Autowired
    private RemoveBankAccountService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter personal code: ");
        String personalCode = scanner.nextLine();
        RemoveBankAccountRequest request = new RemoveBankAccountRequest(personalCode);
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
