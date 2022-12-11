package lv.javaguru.java2.tasksScheduler.core.services.menu_services;

import lv.javaguru.java2.tasksScheduler.core.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.Settings;
import lv.javaguru.java2.tasksScheduler.core.requests.AddSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AddSettingsResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.services.validators.AddSettingsValidator;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddSettingsService {

    @Autowired
    private SettingsRepository settingsRepository;
    @Autowired private AddSettingsValidator validator;

    public AddSettingsResponse execute(AddSettingsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddSettingsResponse(errors);
        }

        Settings settings = new Settings(Encryption.stringHashing(request.getAdminPassword()),
                request.getEmailFrom(), request.getEmailPassword(), request.getEmailHost(),
                request.getEmailPort(), request.getEmailProtocol());

        if (!settingsRepository.save(settings)) {
            errors.add(new CoreError("System", "Error adding to repository."));
            return new AddSettingsResponse(errors);
        }

        return new AddSettingsResponse(settings);
    }
}
