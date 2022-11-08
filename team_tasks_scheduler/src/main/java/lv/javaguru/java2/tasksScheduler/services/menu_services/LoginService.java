package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;


import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.LoginResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.services.validators.LoginValidator;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class LoginService {

    @Autowired private UsersRepository usersRepository;
    @Autowired
    private LoginValidator validator;
    @Autowired private TasksRepository tasksRepository;
    @Autowired private SessionService sessionService;

    public LoginResponse execute(LoginRequest request) {

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new LoginResponse(errors);
        }

        User user = usersRepository.getUserByNameAndPassword(request.getUserName(),
                                             Encryption.stringHashing(request.getPassword()));

        sessionService.login(user.getId(), request.getPassword());
        tasksRepository.deleteByUserIdTillDate(sessionService.getCurrentUserId(), LocalDateTime.now());
        return new LoginResponse(user);
    }
}
