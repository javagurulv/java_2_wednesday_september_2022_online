package myApp.consoleUI;

import myApp.core.requests.SeeYourAccountRequest;
import myApp.core.responses.SeeYourAccountResponse;
import myApp.core.services.SeeYourAccountService;
import myApp.core.services.UserService;

public class SeeYourAccountUIAction implements UIAction {

    private SeeYourAccountService service;
    private UserService userService;

    public SeeYourAccountUIAction(SeeYourAccountService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @Override
    public void execute() {
        String personalCode = userService.getPersonalCode();
        SeeYourAccountRequest request = new SeeYourAccountRequest(personalCode);
        SeeYourAccountResponse response = service.execute(request);
        System.out.println(response.getBankAccount().get());
    }
}
