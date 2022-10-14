package myApp.core.services;


import myApp.core.requests.LogInRequest;
import myApp.core.requests.SwitchUserRequest;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

@DIComponent
public class SwitchUserService {
    @DIDependency
    private UserService service;
    @DIDependency
    private LogInService logInService;

    public String execute(SwitchUserRequest request) {
        service.logOut();
        return logInService.execute(new LogInRequest(request.getPersonalCode(), request.getPassword()))
                .getPersonalCode();
    }

}
