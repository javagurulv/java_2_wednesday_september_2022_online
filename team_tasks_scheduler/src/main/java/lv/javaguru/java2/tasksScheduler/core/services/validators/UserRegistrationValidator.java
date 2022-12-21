package lv.javaguru.java2.tasksScheduler.core.services.validators;


import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaUsersRepository;
import lv.javaguru.java2.tasksScheduler.core.requests.UserRegistrationRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRegistrationValidator {
    @Autowired private JpaUsersRepository usersRepository;

    public List<CoreError> validate(UserRegistrationRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateUserName(request).ifPresent(errors::add);
        validateUserPassword(request).ifPresent(errors::add);
        validateUserEmail(request).ifPresent(errors::add);
        validateIfDuplicate(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateUserName(UserRegistrationRequest request) {
        if (ValueChecking.stringIsEmpty(request.getUsername()) ||
                request.getUsername().length() < 4) {
            return Optional.of(new CoreError("User name", "Has to be longer than 3 chars!"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserEmail(UserRegistrationRequest request) {
        if (ValueChecking.stringIsEmpty(request.getEmail()) ||
                !request.getEmail().contains("@")) {
            return Optional.of(new CoreError("E-mail", "Has to contain char '@'!"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserPassword(UserRegistrationRequest request) {
        if (ValueChecking.stringIsEmpty(request.getPassword()) ||
                request.getPassword().length() < 4
            ) {
            return Optional.of(new CoreError("Password", "Should be >3 characters!"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateIfDuplicate(UserRegistrationRequest request) {
        if (request == null) {
            return Optional.empty();
        }
        if (usersRepository.existsByName(request.getUsername())) {
            return Optional.of(new CoreError("Username", "This username is already used!"));
        }
        return Optional.empty();
    }
}
