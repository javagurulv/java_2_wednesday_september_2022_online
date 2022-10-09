package lv.javaguru.java2.tasksScheduler.services.system;

import lv.javaguru.java2.tasksScheduler.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.requests.CheckSettingsRequest;
import lv.javaguru.java2.tasksScheduler.responses.CheckSettingsResponse;

public class CheckSettingsExistenceService {

    private SettingsRepository settingsRepository;

    public CheckSettingsExistenceService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public CheckSettingsResponse execute(CheckSettingsRequest request) {
        return new CheckSettingsResponse(settingsRepository.recordExists());
    }
}
