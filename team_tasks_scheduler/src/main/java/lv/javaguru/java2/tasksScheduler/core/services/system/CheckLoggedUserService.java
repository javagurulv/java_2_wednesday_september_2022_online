package lv.javaguru.java2.tasksScheduler.core.services.system;

import lv.javaguru.java2.tasksScheduler.core.requests.CheckLoggedUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.CheckSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CheckLoggedUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckLoggedUserService {

    @Autowired
    private SessionService sessionService;

    public CheckLoggedUserResponse execute(CheckLoggedUserRequest request) {
        boolean result;

        result = sessionService.isUserLoggedIn(request.getSessionId());

        return new CheckLoggedUserResponse(result);
    }

}
