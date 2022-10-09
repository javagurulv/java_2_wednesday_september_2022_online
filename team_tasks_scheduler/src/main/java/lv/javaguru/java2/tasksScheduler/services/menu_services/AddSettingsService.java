package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.domain.Settings;
import lv.javaguru.java2.tasksScheduler.requests.AddSettingsRequest;
import lv.javaguru.java2.tasksScheduler.responses.AddSettingsResponse;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.services.validators.AddSettingsValidator;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

import java.util.List;

public class AddSettingsService {

    private SettingsRepository settingsRepository;
    private AddSettingsValidator validator;

    public AddSettingsService(SettingsRepository settingsRepository, AddSettingsValidator validator) {
        this.settingsRepository = settingsRepository;
        this.validator = validator;
    }

    public AddSettingsResponse execute(AddSettingsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddSettingsResponse(errors);
        }

        Settings settings = new Settings(Encryption.stringHashing(request.getAdminPassword()),
                request.getEmailFrom(), request.getEmailPassword(), request.getEmailHost(),
                request.getEmailPort(), request.getEmailProtocol());

        settingsRepository.save(settings);

        return new AddSettingsResponse(settings);
    }
}
