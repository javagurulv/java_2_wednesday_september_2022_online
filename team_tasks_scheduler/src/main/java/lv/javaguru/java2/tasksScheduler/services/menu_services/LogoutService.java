package lv.javaguru.java2.tasksScheduler.services.menu_services;



import lv.javaguru.java2.tasksScheduler.requests.LogoutRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.LogoutResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LogoutService {

    @Autowired
    private SessionService sessionService;

    public LogoutResponse execute(LogoutRequest request) {
        List<CoreError> errors = new ArrayList<>();
        sessionService.logOut();
        return new LogoutResponse(errors);
    }
}
