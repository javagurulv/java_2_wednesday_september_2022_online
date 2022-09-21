package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.User;

import java.util.List;

public interface UsersRepository {

    void save(User user);

    void deleteById(Long id);

    void update(User user);

    boolean exists(String username);

    User getUserById(Long id);

    List<User> getAllUsers();
}
