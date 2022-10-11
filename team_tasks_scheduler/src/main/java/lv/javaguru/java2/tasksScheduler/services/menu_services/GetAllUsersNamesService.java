package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.GetAllUsersNamesRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetAllUsersNamesResponse;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GetAllUsersNamesService {

    private UsersRepository usersRepository;

    public GetAllUsersNamesService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public GetAllUsersNamesResponse execute(GetAllUsersNamesRequest request) {
        List<String> userNames = usersRepository.getAllUsers().stream().map(User::getUsername).collect(toList());

        return new GetAllUsersNamesResponse(userNames, null);
    }
}
