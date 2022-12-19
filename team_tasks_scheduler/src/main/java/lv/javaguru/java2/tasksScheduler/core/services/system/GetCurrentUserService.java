package lv.javaguru.java2.tasksScheduler.core.services.system;

import lv.javaguru.java2.tasksScheduler.core.database.UsersRepository;


import lv.javaguru.java2.tasksScheduler.core.domain.User;
import lv.javaguru.java2.tasksScheduler.core.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.GetCurrentUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetCurrentUserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired private SessionService sessionService;

    public GetCurrentUserResponse execute(GetCurrentUserRequest request) {
        User result = new User(usersRepository.getUserById(sessionService.getCurrentUserId()));
        if (request.isDecryptedPassword()) {
            result.setPassword(sessionService.getDecryptedPassword());
        }
        return new GetCurrentUserResponse(result);
    }
}
