package lv.javaguru.java2.tasksScheduler.core.services.menu_services;

import lv.javaguru.java2.tasksScheduler.core.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.core.database.UsersRepository;


import lv.javaguru.java2.tasksScheduler.core.requests.DeleteCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.responses.DeleteCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DeleteCurrentUserService {

    @Autowired private UsersRepository usersRepository;
    @Autowired private TasksRepository tasksRepository;
    @Autowired private SessionService sessionService;

    public DeleteCurrentUserResponse execute(DeleteCurrentUserRequest request) {
        try {
            String deletedUserName = usersRepository.getUserById(sessionService.getCurrentUserId()).getUsername();
            tasksRepository.deleteByUserIdTillDate(sessionService.getCurrentUserId(), LocalDateTime.MAX);
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
