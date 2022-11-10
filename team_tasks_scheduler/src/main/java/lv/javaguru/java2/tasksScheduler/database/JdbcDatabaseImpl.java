package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcDatabaseImpl implements UsersRepository {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public boolean save(User user) {
        int result;
        result = jdbcTemplate.update(
                "INSERT INTO users (username, user_password, email, send_reminder) "
                        + " VALUES (?, ?, ?, ?)",
                user.getUsername(), user.getPassword(), user.getEmail(), user.getSendReminders()
        );
        return result == 1;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        Object[] args = new Object[] {id};

        jdbcTemplate.update(sql, args);
        //TODO check return value??
        //return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean existsByName(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        Object[] args = new Object[] {username};
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), args);

        return !users.isEmpty();
    }

    @Override
    public User getUserById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        Object[] args = new Object[] {id};
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), args);

        return users.get(0);
    }

    @Override
    public User getUserByNameAndPassword(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND user_password = ?";
        Object[] args = new Object[] {username, password};
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), args);

        return users.stream().findAny()
                .orElse(null);
    }

    @Override
    public List<User> getUsersAcceptedReminders() {
        //email doesn't need checking because it is mandatory in DB
        String sql = "SELECT * FROM users WHERE send_reminder = true";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public List<User> getUsersByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username LIKE ?"; //TODO search by phrase?
        Object[] args = new Object[] {"%"+username+"%"}; //adding wildcard to SQL query. for LIKE
        return jdbcTemplate.query(sql, new UserRowMapper(), args);
    }

    @Override
    public List<User> getUsersByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email LIKE '%?%'"; //TODO search by phrase?
        Object[] args = new Object[] {"%"+email+"%"};//adding wildcard to SQL query. for LIKE
        return jdbcTemplate.query(sql, new UserRowMapper(), args);
    }

    @Override
    public List<User> getUsersByUsernameAndEmail(String username, String email) {
        String sql = "SELECT * FROM users WHERE username LIKE ? AND" +
                "email LIKE ?"; //TODO search by phrase?
        Object[] args = new Object[] {"%"+username+"%", "%"+email+"%"}; //adding wildcard
        return jdbcTemplate.query(sql, new UserRowMapper(), args);
    }
}
