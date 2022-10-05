package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.GetAllUsersNameRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetAllUsersNameResponse;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GetAllUsersNamesService {

    private UsersRepository usersRepository;

    public GetAllUsersNamesService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public GetAllUsersNameResponse execute(GetAllUsersNameRequest request) {
        List<User> users = usersRepository.getAllUsers();

        return new GetAllUsersNameResponse(users, 1);
    }
}
