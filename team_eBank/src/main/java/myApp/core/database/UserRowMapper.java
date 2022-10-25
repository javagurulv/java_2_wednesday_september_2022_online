package myApp.core.database;

import myApp.core.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper  implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setPersonalCode(rs.getString("personal_code"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
