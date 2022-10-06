package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.requests.LogoutRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.LogoutResponse;

import java.util.ArrayList;
import java.util.List;

public class LogoutService {

    private SessionService sessionService;

    public LogoutService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public LogoutResponse execute(LogoutRequest request) {
        List<CoreError> errors = new ArrayList<>();
        sessionService.logOut();
        return new LogoutResponse(errors);
    }
}
