package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.UserRegistrationRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.UserRegistrationResponse;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

import java.util.List;

public class UserRegistrationService {

    private UsersRepository usersRepository;
    private UserInfoValidator validator;

    public UserRegistrationService(UsersRepository usersRepository, UserInfoValidator validator) {
        this.usersRepository = usersRepository;
        this.validator = validator;
    }

    public UserRegistrationResponse execute(UserRegistrationRequest request) {
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()) {
            for (CoreError err : errors) {
                System.out.println(err.getField() +err.getMessage());
            }
        }
        if (!errors.isEmpty()) {
            return new UserRegistrationResponse(errors);
        }

        User user = new User(request.getUsername(),
                        Encryption.stringHashing(request.getPassword()),
                        request.getEmail(),
                        request.getMobilePhone());
        usersRepository.save(user);
        //TODO check return value of save()

        return new UserRegistrationResponse(user);
    }
}
