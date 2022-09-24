package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;

import java.util.List;

public class GetAllUsersService {

    private UsersRepository usersRepository;

    public GetAllUsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> execute() {
        return usersRepository.getAllUsers();
    }
}
