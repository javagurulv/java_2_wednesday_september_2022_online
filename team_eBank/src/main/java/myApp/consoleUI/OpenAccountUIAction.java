package myApp.consoleUI;

import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.OpenAccountResponse;
import myApp.core.services.OpenAccountService;
import myApp.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpenAccountUIAction implements UIAction {
    @Autowired
    private OpenAccountService service;
    @Autowired
    private UserService userService;

    @Override
    public void execute() {
        String name = userService.getPersonalCode();
        OpenAccountRequest request = new OpenAccountRequest(name);
        OpenAccountResponse response = service.execute(request);
        System.out.println("Account has added");
    }
}
