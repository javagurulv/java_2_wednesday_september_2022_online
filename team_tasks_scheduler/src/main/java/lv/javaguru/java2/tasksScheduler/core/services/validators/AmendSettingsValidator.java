package lv.javaguru.java2.tasksScheduler.core.services.validators;


import lv.javaguru.java2.tasksScheduler.core.requests.AmendSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AmendSettingsValidator {

    public List<CoreError> validate(AmendSettingsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateAdminPassword(request).ifPresent(errors::add);
        validateEmailFrom(request).ifPresent(errors::add);
        validateEmailPassword(request).ifPresent(errors::add);
        validateEmailHost(request).ifPresent(errors::add);
        validateEmailPort(request).ifPresent(errors::add);
        validateEmailProtocol(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateAdminPassword(AmendSettingsRequest request) {
        return (ValueChecking.stringIsEmpty(request.getAdminPassword()) ||
                request.getAdminPassword().length() < 3)
                ? Optional.of(new CoreError("Administrator password", "Must be > 3 characters!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEmailFrom(AmendSettingsRequest request) {
        return (ValueChecking.stringIsEmpty(request.getEmailFrom()) ||
                !request.getEmailFrom().contains("@"))
                ? Optional.of(new CoreError("Email from", "Must be provided and contain char '@'!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEmailPassword(AmendSettingsRequest request) {
        return (ValueChecking.stringIsEmpty(request.getEmailPassword()))
                ? Optional.of(new CoreError("Email password", "Must be provided!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEmailHost(AmendSettingsRequest request) {
        return (ValueChecking.stringIsEmpty(request.getEmailHost()))
                ? Optional.of(new CoreError("Email host", "Must be provided!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEmailPort(AmendSettingsRequest request) {
        return (ValueChecking.stringIsEmpty(request.getEmailPort()) ||
                !ValueChecking.stringIsInteger(request.getEmailPort()))
                ? Optional.of(new CoreError("Email port", "Must be provided and be an integer!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEmailProtocol(AmendSettingsRequest request) {
        return (ValueChecking.stringIsEmpty(request.getEmailProtocol()))
                ? Optional.of(new CoreError("Email protocol", "Must be provided!"))
                : Optional.empty();
    }
}
