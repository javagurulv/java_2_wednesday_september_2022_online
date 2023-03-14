package lv.javaguru.java2.tasksScheduler.core.services.menu_services;

import lv.javaguru.java2.tasksScheduler.core.requests.ExitSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.ExitSettingsResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class SettingsLogoutService {

    @Autowired
    SessionService sessionService;

    public ExitSettingsResponse execute(ExitSettingsRequest request) {

        if (request.getSessionId() == null) {
            sessionService.logOut();
        } else {
            sessionService.webLogout(request.getSessionId());
        }

        return new ExitSettingsResponse();
    }
}
