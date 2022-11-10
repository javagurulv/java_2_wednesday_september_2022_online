package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.Settings;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingsRowMapper implements RowMapper<Settings> {
    @Override
    public Settings mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
