package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.requests.DeleteCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.DeleteCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class DeleteCurrentUserService {

    @DIDependency private UsersRepository usersRepository;
    @DIDependency private TasksRepository tasksRepository;
    @DIDependency private SessionService sessionService;

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
