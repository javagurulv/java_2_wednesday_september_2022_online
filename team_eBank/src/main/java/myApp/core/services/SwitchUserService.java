package myApp.core.services;


public class SwitchUserService {

    private UserService service;

    public SwitchUserService(UserService service) {
        this.service = service;
    }

    public String execute(String personalCode) {
        service.logOut();
        return service.logIn(personalCode);
    }

}
