package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.AmendCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.responses.AmendCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

import java.util.ArrayList;
import java.util.List;

public class AmendCurrentUserService {

    private UsersRepository usersRepository;
    private SessionService sessionService;
    private UserAmendValidator validator;

    public AmendCurrentUserService(UsersRepository usersRepository, SessionService sessionService,
                                   UserAmendValidator validator) {
        this.usersRepository = usersRepository;
        this.sessionService = sessionService;
        this.validator = validator;
    }

    public AmendCurrentUserResponse execute(AmendCurrentUserRequest request) {
        User currentUser = usersRepository.getUserById(sessionService.getCurrentUserId());
        if (currentUser == null) {
            List<CoreError> errors = new ArrayList<>();
            errors.add(new CoreError("User ID", "User does not exist"));
            return new AmendCurrentUserResponse(errors);
        }

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AmendCurrentUserResponse(errors);
        }

        User amendedUser = new User(request.getUsername(), Encryption.stringHashing(request.getPassword()),
                request.getEmail(), request.getMobilePhone());
        amendedUser.setId(currentUser.getId());
        usersRepository.update(amendedUser);
        sessionService.setDecryptedPassword(request.getPassword());
        return new AmendCurrentUserResponse(amendedUser);
    }
}
