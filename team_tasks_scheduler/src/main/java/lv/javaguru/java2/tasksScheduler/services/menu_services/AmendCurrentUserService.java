package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;


import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.AmendCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.responses.AmendCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.services.validators.UserAmendValidator;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AmendCurrentUserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired private SessionService sessionService;
    @Autowired private UserAmendValidator validator;

    public AmendCurrentUserResponse execute(AmendCurrentUserRequest request) {
        List<CoreError> errors;
        User currentUser = usersRepository.getUserById(sessionService.getCurrentUserId());
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

        if (currentUser.equals(amendedUser))
            return null;

         if (!usersRepository.update(amendedUser)) {
             errors = new ArrayList<>();
             errors.add(new CoreError("Users repository", "Update failed."));
             return new AmendCurrentUserResponse(errors);
         }
        sessionService.setDecryptedPassword(request.getPassword());
        return new AmendCurrentUserResponse(amendedUser);
    }
}
