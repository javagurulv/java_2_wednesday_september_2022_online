package lv.javaguru.java2.tasksScheduler.core.services.menu_services;


import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaTasksRepository;
import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaUsersRepository;
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

    @Autowired private JpaUsersRepository usersRepository;
    @Autowired private JpaTasksRepository tasksRepository;
    @Autowired private SessionService sessionService;

    public DeleteCurrentUserResponse execute(DeleteCurrentUserRequest request) {
        Long userId;
        //request came from console ui or web ui
        if (request.getSessionId() == null) {
            userId = sessionService.getCurrentUserId();
        } else {
            userId = sessionService.webGetCurrentUserId(request.getSessionId());
        }
        try {
            String deletedUserName = usersRepository.findUserById(userId).getUsername();
            tasksRepository.deleteByUserIdTillDate(userId, LocalDateTime.MAX);
            usersRepository.deleteById(userId);
            if (request.getSessionId() == null) {
                sessionService.logOut();
            } else {
                sessionService.webLogout(request.getSessionId());
            }
            return new DeleteCurrentUserResponse(deletedUserName);
        } catch (RuntimeException e) {
            List<CoreError> error = new ArrayList<>(1);
            error.add(new CoreError("Username", " Something went wrong"));
            return new DeleteCurrentUserResponse(error);
        }

    }
}
