package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.LoginResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

import java.util.ArrayList;
import java.util.List;

public class LoginService {

    private UsersRepository usersRepository;
    private TasksRepository tasksRepository;
    private SessionService sessionService;

    public LoginService(UsersRepository usersRepository,
                        TasksRepository tasksRepository,
                        SessionService sessionService) {
        this.usersRepository = usersRepository;
        this.tasksRepository = tasksRepository;
        this.sessionService = sessionService;
    }

    public LoginResponse execute(LoginRequest request) {
        List<CoreError> errors = new ArrayList<>();
        User user = usersRepository.getUserByNameAndPassword(request.getUserName(),
                                             Encryption.stringHashing(request.getPassword()));
        if (user == null) {
            errors.add(new CoreError("Username", "May be incorrect"));
            errors.add(new CoreError("Password", "May be incorrect"));
            return new LoginResponse(user, errors);
        } else {
            sessionService.login(user.getId(), request.getPassword());
            tasksRepository.deleteOutOfDateByUserId(sessionService.getCurrentUserId());
            return new LoginResponse(user, errors);
        }
    }
}
