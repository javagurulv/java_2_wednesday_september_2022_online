package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.requests.ExitSettingsRequest;
import lv.javaguru.java2.tasksScheduler.requests.LogoutRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.ExitSettingsResponse;
import lv.javaguru.java2.tasksScheduler.responses.LogoutResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;

import java.util.ArrayList;
import java.util.List;

public class ExitSettingsService {

    private SessionService sessionService;

    public ExitSettingsService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public ExitSettingsResponse execute(ExitSettingsRequest request) {
        sessionService.logOut();
        return new ExitSettingsResponse();
    }
}
