package myApp.core.services;


import myApp.core.requests.LogInRequest;

public class SwitchUserService {

    private UserService service;
    private LogInService logInService;
    public SwitchUserService(UserService service, LogInService logInService) {
        this.service = service;
        this.logInService = logInService;
    }

    public String execute(String personalCode, String password) {
        service.logOut();
        return logInService.execute(new LogInRequest(personalCode, password)).getPersonalCode();
    }

}
