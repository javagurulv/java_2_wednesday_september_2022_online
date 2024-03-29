package lv.javaguru.java2.tasksScheduler.core.services.menu_services;


import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaUsersRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.User;
import lv.javaguru.java2.tasksScheduler.core.requests.AmendCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AmendCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.core.services.validators.UserAmendValidator;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class AmendCurrentUserService {

    @Autowired private JpaUsersRepository usersRepository;
    @Autowired private SessionService sessionService;
    @Autowired private UserAmendValidator validator;

    public AmendCurrentUserResponse execute(AmendCurrentUserRequest request) {
        List<CoreError> errors;

        Long userId = sessionService.getCurrentUserId(request.getSessionId());
        User currentUser = usersRepository.findUserById(userId);
        if (currentUser == null) {
            errors = new ArrayList<>();
            errors.add(new CoreError("User ID", "User does not exist"));
            return new AmendCurrentUserResponse(errors);
        }

        errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AmendCurrentUserResponse(errors);
        }

        User amendedUser = new User(request.getUsername(), Encryption.stringHashing(request.getPassword()),
                request.getEmail(), request.isSendReminders());
        amendedUser.setId(currentUser.getId());

        if (currentUser.equals(amendedUser)) {
            return new AmendCurrentUserResponse(amendedUser);
        }

        if (!usersRepository.update(amendedUser)) {
             errors = new ArrayList<>();
             errors.add(new CoreError("Users repository", "Update failed."));
             return new AmendCurrentUserResponse(errors);
        }

        sessionService.setDecryptedPassword(request.getPassword(), request.getSessionId());

        return new AmendCurrentUserResponse(amendedUser);
    }
}
