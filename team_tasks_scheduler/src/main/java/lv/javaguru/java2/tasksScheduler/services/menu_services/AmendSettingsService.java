package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.domain.Settings;
import lv.javaguru.java2.tasksScheduler.requests.AddSettingsRequest;
import lv.javaguru.java2.tasksScheduler.requests.AmendSettingsRequest;
import lv.javaguru.java2.tasksScheduler.responses.AddSettingsResponse;
import lv.javaguru.java2.tasksScheduler.responses.AmendSettingsResponse;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.services.validators.AddSettingsValidator;
import lv.javaguru.java2.tasksScheduler.services.validators.AmendSettingsValidator;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

import java.util.ArrayList;
import java.util.List;

public class AmendSettingsService {

    private SettingsRepository settingsRepository;
    private AmendSettingsValidator validator;
    private SessionService sessionService;

    public AmendSettingsService(SettingsRepository settingsRepository, AmendSettingsValidator validator, SessionService sessionService) {
        this.settingsRepository = settingsRepository;
        this.validator = validator;
        this.sessionService = sessionService;
    }

    public AmendSettingsResponse execute(AmendSettingsRequest request) {
        List<CoreError> errors;
        Settings currentSettings = settingsRepository.getRecord();
        if (currentSettings == null) {
            errors = new ArrayList<>();
            errors.add(new CoreError("Settings record", "Does not exist."));
            return new AmendSettingsResponse(errors);
        }

        errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AmendSettingsResponse(errors);
        }

        Settings amendedSettings = new Settings(Encryption.stringHashing(request.getAdminPassword()),
                request.getEmailFrom(), request.getEmailPassword(), request.getEmailHost(),
                request.getEmailPort(), request.getEmailProtocol());

        if (!settingsRepository.save(amendedSettings)) {
            errors = new ArrayList<>();
            errors.add(new CoreError("Settings repository", "Update failed."));
            return new AmendSettingsResponse(errors);
        }
        sessionService.setDecryptedPassword(request.getAdminPassword());
        return new AmendSettingsResponse(amendedSettings);
    }
}
