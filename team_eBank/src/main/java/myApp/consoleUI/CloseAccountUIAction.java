package myApp.consoleUI;

import myApp.core.requests.CloseAccountRequest;

import myApp.core.responses.CloseAccountResponse;
import myApp.core.services.CloseAccountService;
import myApp.core.services.UserService;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

@DIComponent
public class CloseAccountUIAction implements UIAction {
    @DIDependency
    private CloseAccountService service;
    @DIDependency
    private UserService userService;

    @Override
    public void execute() {
        String personalCode = userService.getPersonalCode();
        CloseAccountRequest request = new CloseAccountRequest(personalCode);
        CloseAccountResponse response = service.execute(request);
        if (response.isDeleted()) {
            System.out.println("Account has been closed");
        } else {
            System.out.println("Error");
        }

    }
}
