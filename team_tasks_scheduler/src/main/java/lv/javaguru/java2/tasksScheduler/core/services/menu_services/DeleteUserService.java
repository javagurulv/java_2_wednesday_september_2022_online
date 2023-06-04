package lv.javaguru.java2.tasksScheduler.core.services.menu_services;


import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaTasksRepository;
import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaUsersRepository;
import lv.javaguru.java2.tasksScheduler.core.requests.DeleteCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.DeleteUserRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.responses.DeleteCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.DeleteUserResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DeleteUserService {

    @Autowired private JpaUsersRepository usersRepository;
    @Autowired private JpaTasksRepository tasksRepository;

    public DeleteUserResponse execute(DeleteUserRequest request) {
        try {
            String deletedUserName = usersRepository.findUserById(request.getUserId()).getUsername();
            tasksRepository.deleteByUserIdTillDate(request.getUserId(), LocalDateTime.MAX);
            usersRepository.deleteById(request.getUserId());
            return new DeleteUserResponse(deletedUserName);
        } catch (RuntimeException e) {
            List<CoreError> error = new ArrayList<>(1);
            error.add(new CoreError("Username", " Something went wrong"));
            return new DeleteUserResponse(error);
        }
    }
}
