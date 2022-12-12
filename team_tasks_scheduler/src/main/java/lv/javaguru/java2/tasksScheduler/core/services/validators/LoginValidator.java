package lv.javaguru.java2.tasksScheduler.core.services.validators;

import lv.javaguru.java2.tasksScheduler.core.database.UsersRepository;

import lv.javaguru.java2.tasksScheduler.core.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LoginValidator {

    @Autowired
    private UsersRepository usersRepository;

    public List<CoreError> validate(LoginRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateUsernameIsProvided(request).ifPresent(errors::add);
        validatePasswordIsProvided(request).ifPresent(errors::add);
        validateCredentials(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateUsernameIsProvided(LoginRequest request) {
        return (ValueChecking.stringIsEmpty(request.getUserName()))
                ? Optional.of(new CoreError("Username", "Must be provided."))
                : Optional.empty();
    }

    private Optional<CoreError> validatePasswordIsProvided(LoginRequest request) {
        return (ValueChecking.stringIsEmpty(request.getPassword()))
                ? Optional.of(new CoreError("Password", "Must be provided."))
                : Optional.empty();
    }

    private Optional<CoreError> validateCredentials(LoginRequest request) {
        if (request == null || request.getUserName() == null || request.getPassword() == null ||
                request.getUserName().isBlank() || request.getPassword().isBlank()) {
            return Optional.empty();
        }
        return (usersRepository.getUserByNameAndPassword(request.getUserName(),
                Encryption.stringHashing(request.getPassword())) == null)
                ? Optional.of(new CoreError("Username/Password", "Invalid credentials provided."))
                : Optional.empty();
    }


}
