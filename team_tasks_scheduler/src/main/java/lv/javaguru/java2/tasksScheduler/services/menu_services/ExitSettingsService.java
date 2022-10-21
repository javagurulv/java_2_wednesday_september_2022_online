package lv.javaguru.java2.tasksScheduler.services.menu_services;



import lv.javaguru.java2.tasksScheduler.requests.ExitSettingsRequest;
import lv.javaguru.java2.tasksScheduler.requests.LogoutRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.ExitSettingsResponse;
import lv.javaguru.java2.tasksScheduler.responses.LogoutResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExitSettingsService {

    @Autowired
    private SessionService sessionService;

    public ExitSettingsResponse execute(ExitSettingsRequest request) {
        sessionService.logOut();
        return new ExitSettingsResponse();
    }
}
