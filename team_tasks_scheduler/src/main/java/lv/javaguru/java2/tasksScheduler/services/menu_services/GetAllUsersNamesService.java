package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.GetAllUsersNamesRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetAllUsersNamesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class GetAllUsersNamesService {

   @Autowired
   private UsersRepository usersRepository;

    public GetAllUsersNamesResponse execute(GetAllUsersNamesRequest request) {
        List<String> userNames = usersRepository.getAllUsers().stream().map(User::getUsername).collect(toList());

        return new GetAllUsersNamesResponse(userNames, null);
    }
}
