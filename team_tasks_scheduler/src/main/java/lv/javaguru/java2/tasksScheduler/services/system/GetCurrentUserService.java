package lv.javaguru.java2.tasksScheduler.services.system;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetCurrentUserResponse;

@DIComponent
public class GetCurrentUserService {

    @DIDependency private UsersRepository usersRepository;
    @DIDependency private SessionService sessionService;

    public GetCurrentUserResponse execute(GetCurrentUserRequest request) {
        User result = usersRepository.getUserById(sessionService.getCurrentUserId());
        result.setPassword(sessionService.getDecryptedPassword());
        return new GetCurrentUserResponse(result);
    }
}
