package myApp.consoleUI;

import myApp.core.services.UserService;

public class UserServiceUIAction implements UIAction {

    private UserService service;

    public UserServiceUIAction(UserService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        service.logOut();
    }
}
