package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.database.SettingsRepository;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.requests.AddSettingsRequest;
import lv.javaguru.java2.tasksScheduler.requests.SettingsLoginRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SettingsLoginValidator {

    @Autowired
    private SettingsRepository settingsRepository;

    public List<CoreError> validate(SettingsLoginRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateAdminPassword(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateAdminPassword(SettingsLoginRequest request) {
        return (ValueChecking.stringIsEmpty(request.getAdminPassword()) ||
                !settingsRepository.passwordIsValid(Encryption.stringHashing(request.getAdminPassword())))
                ? Optional.of(new CoreError("Administrator password", "Is invalid."))
                : Optional.empty();
    }
}
