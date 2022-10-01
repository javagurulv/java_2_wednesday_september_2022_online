package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.requests.LogoutRequest;
import lv.javaguru.java2.tasksScheduler.responses.LogoutResponse;

public class LogoutService {

    private SessionService sessionService;

    public LogoutService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public LogoutResponse execute(LogoutRequest request) {
        sessionService.logOut();
        return new LogoutResponse();
    }
}
