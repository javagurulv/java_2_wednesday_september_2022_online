package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;

public class GetCurrentUserService {

    private UsersRepository usersRepository;
    private SessionService sessionService;

    public GetCurrentUserService(UsersRepository usersRepository, SessionService sessionService) {
        this.usersRepository = usersRepository;
        this.sessionService = sessionService;
    }

    public GetCurrentUserResponse execute(GetCurrentUserRequest request) {

        User result = usersRepository.getUserById(sessionService.getCurrentUserId());
        result.setPassword(sessionService.getDecryptedPassword());

        return new GetCurrentUserResponse(result);
    }
}
