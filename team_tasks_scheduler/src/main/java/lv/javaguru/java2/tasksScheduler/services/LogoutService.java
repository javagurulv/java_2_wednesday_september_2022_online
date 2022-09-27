package lv.javaguru.java2.tasksScheduler.services;

public class LogoutService {

    private SessionService sessionService;

    public LogoutService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public void execute() {
        sessionService.logOut();
    }
}
