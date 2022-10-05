package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.requests.DeleteCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.DeleteCurrentUserResponse;

import java.util.ArrayList;
import java.util.List;

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

    public DeleteCurrentUserResponse execute(DeleteCurrentUserRequest request) {
        try {
            String deletedUserName = usersRepository.getUserById(sessionService.getCurrentUserId()).getUsername();
            tasksRepository.deleteByUserId(sessionService.getCurrentUserId());
            usersRepository.deleteById(sessionService.getCurrentUserId());
            sessionService.logOut();
            return new DeleteCurrentUserResponse(deletedUserName);
        } catch (RuntimeException e) {
            List<CoreError> error = new ArrayList<>(1);
            error.add(new CoreError("Username", " Something went wrong"));
            return new DeleteCurrentUserResponse(error);
        }

    }
}
