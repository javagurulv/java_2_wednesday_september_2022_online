package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Timestamp dueDateSql, endDateSql;
        LocalDateTime dueDate, endDate;

        dueDateSql = rs.getTimestamp("Due_date");   // contains date and time
        endDateSql = rs.getTimestamp("End_date");
        dueDate = new java.sql.Timestamp(dueDateSql.getTime()).toLocalDateTime();
        endDate = new java.sql.Timestamp(endDateSql.getTime()).toLocalDateTime();

        Task task = new Task(rs.getString("task_description"),
                                rs.getInt("regularity"),
                                dueDate,
                                endDate,
                                rs.getLong("user_id"));
        task.setId(rs.getLong("id"));

        return task;
    }
}
