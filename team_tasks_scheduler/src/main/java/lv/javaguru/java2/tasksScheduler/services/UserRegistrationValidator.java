package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.requests.UserRegistrationRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRegistrationValidator {
    public List<CoreError> validate(UserRegistrationRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateUserName(request).ifPresent(errors::add);
        validateUserPassword(request).ifPresent(errors::add);
        validateUserEmail(request).ifPresent(errors::add);
        validateUserPhone(request).ifPresent(errors::add);

        return errors;
    }

    private Optional<CoreError> validateUserName(UserRegistrationRequest request) {
        if (request.getUsername() == null || request.getUsername().isEmpty() ||
                request.getUsername().length() < 3) {
            return Optional.of(new CoreError("User name", "Has to be longer than 3 chars"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserPhone(UserRegistrationRequest request) {
        if (request.getMobilePhone() == null || request.getMobilePhone().isEmpty() ||
                request.getMobilePhone().matches("[0-9]+") != true ||
                request.getMobilePhone().length() < 5) {
            return Optional.of(new CoreError("Phone number", "Has to contain >5 0..9 digits"));
        }
        return Optional.empty();
    }
    private Optional<CoreError> validateUserEmail(UserRegistrationRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty() ||
                request.getEmail().contains("@") != true) {
            return Optional.of(new CoreError("e-mail", "Has to contain char '@'"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserPassword(UserRegistrationRequest request) {
        if (request.getPassword() == null || request.getPassword().isEmpty() ||
                request.getPassword().length() < 3
            ) {
            return Optional.of(new CoreError("Password", "Should be >3 characters"));
        }
        return Optional.empty();
    }
}
