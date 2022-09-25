package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

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

    public boolean execute(String username, String password) {
        User user = usersRepository.getUserByNameAndPassword(username, Encryption.stringHashing(password));
        if (user == null) {
            return false;
        } else {
            sessionService.login(user.getId(), password);
            tasksRepository.deleteOutOfDateByUserId(sessionService.getCurrentUserId());
            return true;
        }
    }
}
