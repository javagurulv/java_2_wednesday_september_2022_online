package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;

import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.AmendCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
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
            return Optional.of(new CoreError("User", "Problem occurs deriving current user details"));
        }
        if (currentUser.getUsername().equals(request.getUsername())) {
            return Optional.empty();
        }
        if (usersRepository.existsByName(request.getUsername())) {
            return Optional.of(new CoreError("User", "Exists in database"));
        }
        return Optional.empty();
    }
    private Optional<CoreError> validateUserName(AmendCurrentUserRequest request) {
        if (ValueChecking.stringIsEmpty(request.getUsername()) ||
                request.getUsername().length() < 3) {
            return Optional.of(new CoreError("User name", "Has to be longer than 3 chars"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserEmail(AmendCurrentUserRequest request) {
        if (ValueChecking.stringIsEmpty(request.getEmail()) ||
                !request.getEmail().contains("@")) {
            return Optional.of(new CoreError("e-mail", "Has to contain char '@'"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserPassword(AmendCurrentUserRequest request) {
        if (ValueChecking.stringIsEmpty(request.getPassword()) ||
                request.getPassword().length() < 3
        ) {
            return Optional.of(new CoreError("Password", "Should be >3 characters"));
        }
        return Optional.empty();
    }

}
