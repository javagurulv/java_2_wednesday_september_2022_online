package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
