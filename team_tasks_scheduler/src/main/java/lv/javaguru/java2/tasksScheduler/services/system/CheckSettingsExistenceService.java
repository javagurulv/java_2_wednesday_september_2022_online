package lv.javaguru.java2.tasksScheduler.services.system;

import lv.javaguru.java2.tasksScheduler.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.requests.CheckSettingsRequest;
import lv.javaguru.java2.tasksScheduler.responses.CheckSettingsResponse;

@DIComponent
public class CheckSettingsExistenceService {

    @DIDependency private SettingsRepository settingsRepository;

    public CheckSettingsResponse execute(CheckSettingsRequest request) {
        return new CheckSettingsResponse(settingsRepository.recordExists());
    }
}
