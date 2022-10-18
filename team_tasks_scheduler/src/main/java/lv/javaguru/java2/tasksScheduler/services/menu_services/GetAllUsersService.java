package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.requests.GetAllUsersRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetAllUsersResponse;

import java.util.List;
import java.util.stream.Collectors;

@DIComponent
public class GetAllUsersService {

    @DIDependency private UsersRepository usersRepository;

    public GetAllUsersResponse execute(GetAllUsersRequest request) {

            return new GetAllUsersResponse(usersRepository.getAllUsers(), null);
    }
}
