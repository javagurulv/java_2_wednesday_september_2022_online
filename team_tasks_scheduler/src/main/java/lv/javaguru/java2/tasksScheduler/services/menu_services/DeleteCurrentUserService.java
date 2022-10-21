package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;


import lv.javaguru.java2.tasksScheduler.requests.DeleteCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.DeleteCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteCurrentUserService {

    @Autowired private UsersRepository usersRepository;
    @Autowired private TasksRepository tasksRepository;
    @Autowired private SessionService sessionService;

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
