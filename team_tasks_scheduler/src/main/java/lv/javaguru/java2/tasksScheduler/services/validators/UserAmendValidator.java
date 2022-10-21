package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;

import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.AmendCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserAmendValidator {
    public List<CoreError> validate(AmendCurrentUserRequest request, UsersRepository userList) {
        List<CoreError> errors = new ArrayList<>();

        validateDuplicate(request, userList).ifPresent(errors::add);
        validateUserName(request).ifPresent(errors::add);
        validateUserPassword(request).ifPresent(errors::add);
        validateUserEmail(request).ifPresent(errors::add);

        return errors;
    }
    private Optional<CoreError> validateDuplicate(AmendCurrentUserRequest request,
                                                  UsersRepository userList) {

        if (userList.existsByName(request.getUsername()) ||
                    userList.existsByEmail(request.getEmail())) {
            return Optional.of(new CoreError("User", "Exists in database"));
        }
        return Optional.empty();
    }
    private Optional<CoreError> validateUserName(AmendCurrentUserRequest request) {
        if (request.getUsername() == null || request.getUsername().isEmpty() ||
                request.getUsername().length() < 3) {
            return Optional.of(new CoreError("User name", "Has to be longer than 3 chars"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserEmail(AmendCurrentUserRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty() ||
                !request.getEmail().contains("@")) {
            return Optional.of(new CoreError("e-mail", "Has to contain char '@'"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserPassword(AmendCurrentUserRequest request) {
        if (request.getPassword() == null || request.getPassword().isEmpty() ||
                request.getPassword().length() < 3
        ) {
            return Optional.of(new CoreError("Password", "Should be >3 characters"));
        }
        return Optional.empty();
    }

}
