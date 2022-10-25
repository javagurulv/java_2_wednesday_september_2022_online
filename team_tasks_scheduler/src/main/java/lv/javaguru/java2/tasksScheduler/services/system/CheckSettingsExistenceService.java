package lv.javaguru.java2.tasksScheduler.services.system;

import lv.javaguru.java2.tasksScheduler.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.requests.CheckSettingsRequest;
import lv.javaguru.java2.tasksScheduler.responses.CheckSettingsResponse;

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
