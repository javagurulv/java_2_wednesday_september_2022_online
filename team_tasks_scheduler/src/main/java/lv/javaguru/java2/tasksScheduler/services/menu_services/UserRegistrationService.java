package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;


import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.UserRegistrationRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.UserRegistrationResponse;
import lv.javaguru.java2.tasksScheduler.services.validators.UserRegistrationValidator;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRegistrationService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired private UserRegistrationValidator validator;

    public UserRegistrationResponse execute(UserRegistrationRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UserRegistrationResponse(errors);
        }

        User user = new User(request.getUsername(),
                        Encryption.stringHashing(request.getPassword()),
                        request.getEmail(),
                        request.isSendReminders());

        if (!usersRepository.save(user)) {
            errors.add(new CoreError("System","Error adding to repository."));
            return new UserRegistrationResponse(errors);
        }

        return new UserRegistrationResponse(user);
    }
}
