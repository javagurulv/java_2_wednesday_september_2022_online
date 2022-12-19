package lv.javaguru.java2.tasksScheduler.core.services.system;

import lv.javaguru.java2.tasksScheduler.core.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.core.requests.CheckSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CheckSettingsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckSettingsExistenceService {

    @Autowired
    private SettingsRepository settingsRepository;

    public CheckSettingsResponse execute(CheckSettingsRequest request) {
        return new CheckSettingsResponse(settingsRepository.recordExists());
    }
}
