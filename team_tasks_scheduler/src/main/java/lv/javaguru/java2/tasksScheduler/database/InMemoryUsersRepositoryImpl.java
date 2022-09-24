package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUsersRepositoryImpl implements UsersRepository {

    private Long nextId = 1L;
    private List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        if (user == null)
            return;
        user.setId(nextId);
        nextId++;
        users.add(user);
        if (nextId == Long.MAX_VALUE)
            nextId = 1L;
    }

    @Override
    public void deleteById(Long id) {
        users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .ifPresent(user -> users.remove(user));
    }

    @Override
    public void update(User user) {
        if (user == null)
            return;
        if (getUserById(user.getId()) == null)
            return;
        deleteById(user.getId());
        users.add(user);
    }

    @Override
    public boolean existsByName(String username) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }

    @Override
    public boolean existsByNameAndPassword(String username, String password) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username) &&
                        user.getPassword().equals(password));
    }

    @Override
    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}
