package myApp.consoleUI;

import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.AddAccountResponse;
import myApp.core.services.OpenAccountService;
import myApp.core.services.UserService;

public class OpenAccountUIAction implements UIAction {

    private OpenAccountService service;
    private UserService userService;

    public OpenAccountUIAction(OpenAccountService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @Override
    public void execute() {
        String name = userService.getPersonalCode();
        OpenAccountRequest request = new OpenAccountRequest(name);
        AddAccountResponse response = service.execute(request);
        System.out.println("Account has added");
    }
}
