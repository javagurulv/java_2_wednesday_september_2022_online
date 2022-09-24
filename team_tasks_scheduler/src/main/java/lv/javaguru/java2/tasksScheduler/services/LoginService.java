package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.User;

public class LoginService {

    private UsersRepository usersRepository;

    public LoginService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean execute(String username, String password) {
        return usersRepository.existsByNameAndPassword(username, password);
    }
}
