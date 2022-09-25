package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.User;

public class GetCurrentUserService {

    private UsersRepository usersRepository;
    private SessionService sessionService;

    public GetCurrentUserService(UsersRepository usersRepository, SessionService sessionService) {
        this.usersRepository = usersRepository;
        this.sessionService = sessionService;
    }

    public User execute() {
        User result = usersRepository.getUserById(sessionService.getCurrentUserId());
        result.setPassword(sessionService.getDecryptedPassword());
        return result;
    }
}
