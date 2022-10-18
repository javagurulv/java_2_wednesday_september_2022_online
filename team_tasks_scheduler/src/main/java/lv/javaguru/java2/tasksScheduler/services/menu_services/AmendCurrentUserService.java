package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.AmendCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.responses.AmendCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.services.validators.UserAmendValidator;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class AmendCurrentUserService {

    @DIDependency private UsersRepository usersRepository;
    @DIDependency private SessionService sessionService;
    @DIDependency private UserAmendValidator validator;

    public AmendCurrentUserResponse execute(AmendCurrentUserRequest request) {
        User currentUser = usersRepository.getUserById(sessionService.getCurrentUserId());
        if (currentUser == null) {
            List<CoreError> errors = new ArrayList<>();
            errors.add(new CoreError("User ID", "User does not exist"));
            return new AmendCurrentUserResponse(errors);
        }

        List<CoreError> errors = validator.validate(request, usersRepository);
        if (!errors.isEmpty()) {
            return new AmendCurrentUserResponse(errors);
        }

        User amendedUser = new User(request.getUsername(), Encryption.stringHashing(request.getPassword()),
                request.getEmail(), request.isSendReminders());
        amendedUser.setId(currentUser.getId());
        usersRepository.update(amendedUser);
        sessionService.setDecryptedPassword(request.getPassword());
        return new AmendCurrentUserResponse(amendedUser);
    }
}
