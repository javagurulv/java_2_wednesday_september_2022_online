package lv.javaguru.java2.tasksScheduler.core.services.validators;

import lv.javaguru.java2.tasksScheduler.core.database.UsersRepository;

import lv.javaguru.java2.tasksScheduler.core.domain.User;
import lv.javaguru.java2.tasksScheduler.core.requests.AmendCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserAmendValidator {
    @Autowired private UsersRepository usersRepository;
    @Autowired private SessionService sessionService;

    public List<CoreError> validate(AmendCurrentUserRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateDuplicate(request).ifPresent(errors::add);
        validateUserName(request).ifPresent(errors::add);
        validateUserPassword(request).ifPresent(errors::add);
        validateUserEmail(request).ifPresent(errors::add);

        return errors;
    }
    private Optional<CoreError> validateDuplicate(AmendCurrentUserRequest request) {
        User currentUser = usersRepository.getUserById(sessionService.getCurrentUserId());
        if (currentUser == null) {
            return Optional.of(new CoreError("User", "Problem occurs deriving current user details!"));
        }
        if (currentUser.getUsername().equals(request.getUsername())) {
            return Optional.empty();
        }
        if (usersRepository.existsByName(request.getUsername())) {
            return Optional.of(new CoreError("User", "Already exists in the database!"));
        }
        return Optional.empty();
    }
    private Optional<CoreError> validateUserName(AmendCurrentUserRequest request) {
        if (ValueChecking.stringIsEmpty(request.getUsername()) ||
                request.getUsername().length() < 4) {
            return Optional.of(new CoreError("User name", "Has to be longer than 3 chars!"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserEmail(AmendCurrentUserRequest request) {
        if (ValueChecking.stringIsEmpty(request.getEmail()) ||
                !request.getEmail().contains("@")) {
            return Optional.of(new CoreError("E-mail", "Has to contain char '@'!"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserPassword(AmendCurrentUserRequest request) {
        if (ValueChecking.stringIsEmpty(request.getPassword()) ||
                request.getPassword().length() < 4
        ) {
            return Optional.of(new CoreError("Password", "Should be > 3 characters!"));
        }
        return Optional.empty();
    }

}
