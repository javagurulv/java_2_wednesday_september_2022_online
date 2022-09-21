package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.User;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;

public class UserRegistrationService {

    private UsersRepository usersRepository;

    public UserRegistrationService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void execute(String username, String password, String email, String mobilePhone) {
        User user = new User(username, password, email, mobilePhone);
        usersRepository.save(user);
    }
}
