package lv.javaguru.java2.tasksScheduler.core.services.menu_services;


import lv.javaguru.java2.tasksScheduler.core.domain.Settings;
import lv.javaguru.java2.tasksScheduler.core.requests.GetSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.SettingsLoginRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.*;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetSettingsService;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.core.services.validators.SettingsLoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettingsLoginService {

    @Autowired private SettingsLoginValidator validator;
    @Autowired private SessionService sessionService;
    @Autowired private GetSettingsService getSettingsService;

    public SettingsLoginResponse execute(SettingsLoginRequest request) {

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SettingsLoginResponse(errors);
        }

        sessionService.login(0L, request.getAdminPassword());
        GetSettingsResponse getSettingsResponse = getSettingsService.execute(new GetSettingsRequest(true));
        Settings settings = getSettingsResponse.getSettings();

        return new SettingsLoginResponse(settings);
    }
}
