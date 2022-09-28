package myApp.consoleUI;

import myApp.core.requests.CloseAccountRequest;

import myApp.core.responses.CloseAccountResponse;
import myApp.core.services.CloseAccountService;
import myApp.core.services.UserService;

public class CloseAccountUIAction implements UIAction {

    private CloseAccountService service;
    private UserService userService;

    public CloseAccountUIAction(CloseAccountService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

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
