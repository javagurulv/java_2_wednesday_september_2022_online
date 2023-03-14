package lv.javaguru.java2.tasksScheduler.core.services.system;

import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaSettingsRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.Settings;
import lv.javaguru.java2.tasksScheduler.core.requests.GetSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.GetSettingsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetSettingsService {

    @Autowired
    private JpaSettingsRepository settingsRepository;
    @Autowired
    private SessionService sessionService;

    public GetSettingsResponse execute(GetSettingsRequest request) {
        Settings settings = new Settings(settingsRepository.getRecord());

        if (request.getSessionId() == null) {
            if (request.isDecryptedPassword()) {
                settings.setAdminPassword(sessionService.getDecryptedPassword());
            }
        } else {
            if (request.isDecryptedPassword()) {
                settings.setAdminPassword(sessionService.webGetDecryptedPassword(request.getSessionId()));
            }
        }

        return new GetSettingsResponse(settings);
    }
}
