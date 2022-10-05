package myApp.core.services;


import myApp.core.requests.LogInRequest;
import myApp.core.requests.SwitchUserRequest;

public class SwitchUserService {

    private UserService service;
    private LogInService logInService;
    public SwitchUserService(UserService service, LogInService logInService) {
        this.service = service;
        this.logInService = logInService;
    }

    public String execute(SwitchUserRequest request) {
        service.logOut();
        return logInService.execute(new LogInRequest(request.getPersonalCode(), request.getPassword()))
                .getPersonalCode();
    }

}
