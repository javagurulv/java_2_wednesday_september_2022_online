package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.User;

import java.util.List;

public interface UsersRepository {

    void save(User user);

    void deleteById(Long id);

    void update(User user);

    boolean existsByName(String username);

    boolean existsByNameAndPassword(String username, String password);

    User getUserById(Long id);

    List<User> getAllUsers();
}
