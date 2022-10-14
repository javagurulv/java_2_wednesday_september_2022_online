package myApp.consoleUI;

import myApp.core.requests.SeeYourAccountRequest;
import myApp.core.responses.SeeYourAccountResponse;
import myApp.core.services.SeeYourAccountService;
import myApp.core.services.UserService;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

@DIComponent
public class SeeYourAccountUIAction implements UIAction {
    @DIDependency
    private SeeYourAccountService service;
    @DIDependency
    private UserService userService;

    @Override
    public void execute() {
        String personalCode = userService.getPersonalCode();
        SeeYourAccountRequest request = new SeeYourAccountRequest(personalCode);
        SeeYourAccountResponse response = service.execute(request);
        if (response.getBankAccount().get().getAccount() != null) {
            System.out.println("Account: " + response.getBankAccount().get().getName() + " "
                    + response.getBankAccount().get().getSurname());
            System.out.println("Balance: " + response.getBankAccount().get().getAccount().getBalance());
        } else {
            System.out.println("Account: " + response.getBankAccount().get().getName() + " "
                    + response.getBankAccount().get().getSurname());
            System.out.println("Balance: don't have any account");
        }
    }
}
