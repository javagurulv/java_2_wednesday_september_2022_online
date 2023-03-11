package lv.javaguru.java2.tasksScheduler.core.services.menu_services;



import lv.javaguru.java2.tasksScheduler.core.requests.LogoutRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.responses.LogoutResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LogoutService {

    @Autowired
    private SessionService sessionService;

    public LogoutResponse execute(LogoutRequest request) {

        //request came from console ui
        if (request.getSessionId() == null) {
            sessionService.logOut();
        }
        else {
            sessionService.webLogout(request.getSessionId());
        }

        return new LogoutResponse();
    }
}
