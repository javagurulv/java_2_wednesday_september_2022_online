package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class InMemoryUsersRepositoryImpl implements UsersRepository {

    private Long nextId = 1L;
    private List<User> users = new ArrayList<>();

    @Override
    public boolean save(User user) {
        if (user == null)
            return false;
        user.setId(nextId);
        nextId++;
        users.add(user);
        if (nextId == Long.MAX_VALUE)
            nextId = 1L;
        return true;
    }

    @Override
    public void deleteById(Long id) {
        users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .ifPresent(user -> users.remove(user));
    }

    @Override
    public boolean update(User user) {
        if (user == null)
            return false;
        User currentUser = getUserById(user.getId());
        if (currentUser == null)
            return false;
        users.set(users.indexOf(currentUser), user);
        return true;
    }

    @Override
    public boolean existsByName(String username) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }

    //TODO think if there should be 2 exist() functions
    @Override
    public boolean existsByEmail(String email) {
        return users.stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    @Override
    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public User getUserByNameAndPassword(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username) &&
                        user.getPassword().equals(password))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
