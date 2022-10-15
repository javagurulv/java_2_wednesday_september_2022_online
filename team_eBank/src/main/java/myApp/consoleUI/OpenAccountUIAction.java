package myApp.consoleUI;

import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.OpenAccountResponse;
import myApp.core.services.OpenAccountService;
import myApp.core.services.UserService;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

@DIComponent
public class OpenAccountUIAction implements UIAction {
    @DIDependency
    private OpenAccountService service;
    @DIDependency
    private UserService userService;

    @Override
    public void execute() {
        String name = userService.getPersonalCode();
        OpenAccountRequest request = new OpenAccountRequest(name);
        OpenAccountResponse response = service.execute(request);
        System.out.println("Account has added");
    }
}
