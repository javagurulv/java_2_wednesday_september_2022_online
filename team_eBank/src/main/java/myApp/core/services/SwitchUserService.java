package myApp.core.services;


import myApp.core.requests.LogInRequest;
import myApp.core.requests.SwitchUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SwitchUserService {
    @Autowired
    private UserService service;
    @Autowired
    private LogInService logInService;

    public String execute(SwitchUserRequest request) {
        service.logOut();
        return logInService.execute(new LogInRequest(request.getPersonalCode(), request.getPassword()))
                .getPersonalCode();
    }

}
