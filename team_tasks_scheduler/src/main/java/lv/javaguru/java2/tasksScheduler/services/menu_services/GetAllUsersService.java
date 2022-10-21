package lv.javaguru.java2.tasksScheduler.services.menu_services;



import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.requests.GetAllUsersRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetAllUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllUsersService {

    @Autowired
    private UsersRepository usersRepository;

    public GetAllUsersResponse execute(GetAllUsersRequest request) {

            return new GetAllUsersResponse(usersRepository.getAllUsers(), null);
    }
}
