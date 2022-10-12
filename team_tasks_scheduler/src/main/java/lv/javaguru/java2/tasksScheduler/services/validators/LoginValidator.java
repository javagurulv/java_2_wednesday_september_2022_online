package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.requests.SettingsLoginRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoginValidator {

    public List<CoreError> validate(LoginRequest request, UsersRepository usersRepository) {
        List<CoreError> errors = new ArrayList<>();
        validateUsernameIsProvided(request).ifPresent(errors::add);
        validatePasswordIsProvided(request).ifPresent(errors::add);
        validateCredentials(request, usersRepository).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateUsernameIsProvided(LoginRequest request) {
        return (request.getUserName() == null || request.getUserName().isBlank())
                ? Optional.of(new CoreError("Username", "Must be provided."))
                : Optional.empty();
    }

    private Optional<CoreError> validatePasswordIsProvided(LoginRequest request) {
        return (request.getPassword() == null || request.getPassword().isBlank())
                ? Optional.of(new CoreError("Password", "Must be provided."))
                : Optional.empty();
    }

    private Optional<CoreError> validateCredentials(LoginRequest request, UsersRepository usersRepository) {
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
