package lv.javaguru.java2.tasksScheduler.core.services.menu_services;

import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaSettingsRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.Settings;
import lv.javaguru.java2.tasksScheduler.core.requests.AmendSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AmendSettingsResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.core.services.validators.AmendSettingsValidator;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class AmendSettingsService {

    @Autowired
    private JpaSettingsRepository settingsRepository;
    @Autowired private AmendSettingsValidator validator;
    @Autowired private SessionService sessionService;

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

        if (currentSettings.equals(amendedSettings)) {
            return null;
        }

        var result = settingsRepository.save(amendedSettings); //TODO had to rework after moving to JPA
        if (!result.equals(amendedSettings)) {
            errors = new ArrayList<>();
            errors.add(new CoreError("Settings repository", "Update failed."));
            return new AmendSettingsResponse(errors);
        }
        sessionService.setDecryptedPassword(request.getAdminPassword());
        return new AmendSettingsResponse(amendedSettings);
    }
}
