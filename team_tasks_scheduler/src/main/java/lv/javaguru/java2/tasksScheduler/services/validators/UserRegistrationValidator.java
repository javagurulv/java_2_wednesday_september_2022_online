package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.requests.UserRegistrationRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRegistrationValidator {
    public List<CoreError> validate(UserRegistrationRequest request, UsersRepository usersRepository) {
        List<CoreError> errors = new ArrayList<>();
        validateUserName(request).ifPresent(errors::add);
        validateUserPassword(request).ifPresent(errors::add);
        validateUserEmail(request).ifPresent(errors::add);
        validateIfDuplicate(request, usersRepository).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateUserName(UserRegistrationRequest request) {
        if (request.getUsername() == null || request.getUsername().isEmpty() ||
                request.getUsername().length() < 3) {
            return Optional.of(new CoreError("User name", "Has to be longer than 3 chars"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserEmail(UserRegistrationRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty() ||
                !request.getEmail().contains("@")) {
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

    private Optional<CoreError> validateIfDuplicate(UserRegistrationRequest request, UsersRepository usersRepository) {
        if (request == null) {
            return Optional.empty();
        }
        if (usersRepository.existsByName(request.getUsername())) {
            return Optional.of(new CoreError("Username", "This username is already used."));
        }
        return Optional.empty();
    }
}
