package myApp.core.services.authentication;


import myApp.core.requests.LogInRequest;
import myApp.core.requests.SwitchUserRequest;
import myApp.core.responses.SwitchUserResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

//@Componentomponent
//@Transactionalnsactional
public class SwitchUserService {
    @Autowired
    private UserService service;
    @Autowired
    private LogInService logInService;

    public SwitchUserResponse execute(SwitchUserRequest request) {
        service.logOut();
        return new SwitchUserResponse(logInService.execute(new LogInRequest(request.getLogin(), request.getPassword()))
                .getPersonalCode());
    }

}
