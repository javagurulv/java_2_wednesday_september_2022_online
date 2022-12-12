package lv.javaguru.java2.tasksScheduler.core.database;

import lv.javaguru.java2.tasksScheduler.core.domain.User;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Component;

import java.util.List;


//@Component
public class MongoUserRepository implements UsersRepository {


    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean existsByName(String username) {
        return false;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User getUserByNameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public List<User> getUsersAcceptedReminders() {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public List<User> getUsersByUsername(String username) {
        return null;
    }

    @Override
    public List<User> getUsersByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getUsersByUsernameAndEmail(String username, String email) {
        return null;
    }
}
