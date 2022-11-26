package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class SqlSettingsRepository implements SettingsRepository{

    @Autowired private JdbcTemplate jdbcTemplate;


    @Override
    public boolean save(Settings settings) {
        int result;

        if (settings == null) {
            return false;
        }
        String sql = "INSERT INTO settings (admin_password, email_from, email_password, " +
                "email_host, email_port, email_protocol) "
                + " VALUES (?, ?, ?, ?, ?, ?)";
        Object[] args = new Object[]{settings.getAdminPassword(), settings.getEmailFrom(),
                                    settings.getEmailPassword(), settings.getEmailHost(),
                                    settings.getEmailPort(), settings.getEmailProtocol()};
        result = jdbcTemplate.update(sql, args);

        return result == 1;
    }

    @Override
    public boolean recordExists() {
        String sql = "SELECT * FROM settings";
        List<Settings> settings = jdbcTemplate.query(sql, new SettingsRowMapper());
        if (!settings.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean passwordIsValid(String password) {
        String sql = "SELECT * FROM settings";
        List<Settings> settings = jdbcTemplate.query(sql, new SettingsRowMapper());
        if (!settings.isEmpty()) {
            return settings.get(0).getAdminPassword().equals(password);
        }
        return false;
    }

    @Override
    public Settings getRecord() {
        String sql = "SELECT * FROM settings";
        List<Settings> settings = jdbcTemplate.query(sql, new SettingsRowMapper());

        return settings.stream().findAny().orElse(null);
    }
}
