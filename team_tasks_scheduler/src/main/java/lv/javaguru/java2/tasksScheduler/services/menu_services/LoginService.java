package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.LoginResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.services.validators.LoginValidator;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class LoginService {

    @DIDependency private UsersRepository usersRepository;
    @DIDependency private LoginValidator validator;
    @DIDependency private TasksRepository tasksRepository;
    @DIDependency private SessionService sessionService;

    public LoginResponse execute(LoginRequest request) {

        List<CoreError> errors = validator.validate(request, usersRepository);
        if (!errors.isEmpty()) {
            return new LoginResponse(errors);
        }

        User user = usersRepository.getUserByNameAndPassword(request.getUserName(),
                                             Encryption.stringHashing(request.getPassword()));

        sessionService.login(user.getId(), request.getPassword());
        tasksRepository.deleteOutOfDateByUserId(sessionService.getCurrentUserId());
        return new LoginResponse(user);
    }
}
