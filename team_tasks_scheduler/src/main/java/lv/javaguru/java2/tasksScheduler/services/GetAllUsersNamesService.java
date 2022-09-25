package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GetAllUsersNamesService {

    private UsersRepository usersRepository;

    public GetAllUsersNamesService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<String> execute() {
        return usersRepository.getAllUsers().stream()
                .map(User::getUsername)
                .collect(toList());
    }
}
