package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.Settings;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingsRowMapper implements RowMapper<Settings> {
    @Override
    public Settings mapRow(ResultSet rs, int rowNum) throws SQLException {
        Settings settings = new Settings(rs.getString("admin_password"),
                                            rs.getString("email_from"),
                                            rs.getString("email_password"),
                                            rs.getString("email_host"),
                                            rs.getString("email_port"),
                                            rs.getString("email_protocol"));
        return settings;
    }
}
