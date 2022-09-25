package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;

public class DeleteCurrentUserService {

    private UsersRepository usersRepository;
    private TasksRepository tasksRepository;
    private SessionService sessionService;

    public DeleteCurrentUserService(UsersRepository usersRepository,
                                    TasksRepository tasksRepository,
                                    SessionService sessionService) {
        this.usersRepository = usersRepository;
        this.tasksRepository = tasksRepository;
        this.sessionService = sessionService;
    }

    public String execute() {
        try {
            String deletedUserName = usersRepository.getUserById(sessionService.getCurrentUserId()).getUsername();
            tasksRepository.deleteByUserId(sessionService.getCurrentUserId());
            usersRepository.deleteById(sessionService.getCurrentUserId());
            sessionService.logOut();
            return deletedUserName;
        } catch (RuntimeException e) {
            return null;
        }

    }
}
