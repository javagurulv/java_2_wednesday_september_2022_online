package lv.javaguru.java2.tasksScheduler.core.database;

import lv.javaguru.java2.tasksScheduler.core.domain.User;

import java.util.List;

public interface UsersRepository {

    boolean save(User user);

    boolean deleteById(Long id);

    boolean update(User user);

    boolean existsByName(String username);

    User getUserById(Long id);

    User getUserByNameAndPassword(String username, String password);

    List<User> getUsersAcceptedReminders();

    List<User> getAllUsers();

    List<User> getUsersByUsername(String username);

    List<User> getUsersByEmail(String email);

    List<User> getUsersByUsernameAndEmail(String username, String email);
}
