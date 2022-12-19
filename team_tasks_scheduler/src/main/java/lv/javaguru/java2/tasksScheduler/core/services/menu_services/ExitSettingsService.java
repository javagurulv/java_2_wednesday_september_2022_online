package lv.javaguru.java2.tasksScheduler.core.services.menu_services;



import lv.javaguru.java2.tasksScheduler.core.requests.ExitSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.ExitSettingsResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExitSettingsService {

    @Autowired
    private SessionService sessionService;

    public ExitSettingsResponse execute(ExitSettingsRequest request) {
        sessionService.logOut();
        return new ExitSettingsResponse();
    }
}
