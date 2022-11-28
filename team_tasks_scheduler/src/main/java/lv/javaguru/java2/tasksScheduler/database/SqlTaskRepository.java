package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.enums.SearchDateType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;

import static lv.javaguru.java2.tasksScheduler.utils.ValueChecking.checkAdjustMySqlDateRange;


//@Component
public class SqlTaskRepository implements TasksRepository{

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public boolean save(Task task) {
        if (task == null) {
            return false;
        }

        int result;
        Date dueDateSql = java.sql.Timestamp.valueOf(task.getDueDate());
        Date endDateSql = java.sql.Timestamp.valueOf(task.getEndDate());

        String sql = "INSERT INTO tasks (task_description, regularity, " +
                                                            " Due_date, End_date, user_id) "
                + " VALUES (?, ?, ?, ?, ?)";
        Object[] args = new Object[]{task.getDescription(), task.getRegularity(),
                                        dueDateSql, endDateSql, task.getUserId()};
        result = jdbcTemplate.update(sql, args);

        return result == 1;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        Object[] args = new Object[] {id};
        jdbcTemplate.update(sql, args);

        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public int deleteByUserIdTillDate(Long userId, LocalDateTime endDate) {
        int result;
        String sql;
        Object[] args;
        LocalDateTime pEndDate = checkAdjustMySqlDateRange(endDate);
        Date endDateSql = java.sql.Timestamp.valueOf(pEndDate);

        if (userId == null) {
            sql = "DELETE FROM tasks WHERE end_date < ?";
            args = new Object[] {endDateSql};
        }
        else {
            sql = "DELETE FROM tasks WHERE user_id = ? AND end_date < ?";
            args = new Object[] {userId, endDateSql};
        }
        result = jdbcTemplate.update(sql, args);

        return result;
    }

    @Override
    public boolean update(Task task) {
        if (task == null) {
            return false;
        }

        int result;
        Date dueDateSql = java.sql.Timestamp.valueOf(task.getDueDate());
        Date endDateSql = java.sql.Timestamp.valueOf(task.getEndDate());

        String sql = "UPDATE tasks " +
                    "SET task_description = ?, regularity = ?, due_date = ?, end_date = ? " +
                    " WHERE id = ?";
        Object[] args = new Object[] {task.getDescription(), task.getRegularity(),
                                        dueDateSql, endDateSql, task.getId()};
        result = jdbcTemplate.update(sql, args);

        return result == 1;
    }

    @Override
    public boolean exists(Task task) {
        Date dueDateSql = java.sql.Timestamp.valueOf(task.getDueDate());
        Date endDateSql = java.sql.Timestamp.valueOf(task.getEndDate());

        //check all fields
        String sql = "SELECT * FROM tasks WHERE task_description = ? AND regularity = ? AND " +
                    " due_date = ? AND end_date = ? AND user_id = ?";
        Object[] args = new Object[] {task.getDescription(), task.getRegularity(),
                                    dueDateSql, endDateSql, task.getUserId()};
        List<Task> tasks = jdbcTemplate.query(sql, new TaskRowMapper(), args);
        //should get only one or do not get at all
        return !tasks.isEmpty();
    }

    @Override
    public Task getTaskById(Long id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        Object[] args = new Object[] {id};
        List<Task> tasks = jdbcTemplate.query(sql, new TaskRowMapper(), args);

        //should get only one or do not get at all
        return tasks.stream().findAny().orElse(null);
    }

    @Override
    public List<Task> getAllOutstandingTasksByUserIdTillDate(Long userId, LocalDateTime endDate) {
        LocalDateTime pEndDate;
        Date endDateSql;

        //MySQL date range from '1000-01-01 00:00:00' to '9999-12-31 23:59:59'.
        pEndDate = checkAdjustMySqlDateRange(endDate);
        endDateSql = java.sql.Timestamp.valueOf(pEndDate);

        String sql = "SELECT * FROM tasks WHERE user_id = ? AND due_date < ? AND " +
                        " end_date > LOCALTIME()";
        Object[] args = new Object[] {userId, endDateSql};
        List<Task> tasks = jdbcTemplate.query(sql, new TaskRowMapper(), args);

        return tasks;
    }

    @Override
    public List<Task> getAllTasksReadyForDueDateUpdate(Long userId) {
        String sql;
        Object[] args;
        List<Task> tasks;

        if (userId != null) {
            //MySQL TIMESTAMP(CURDATE()) is returning [DATE+00:00:00]
            sql = "SELECT * FROM tasks WHERE user_id = ? AND (regularity > 0 AND " +
                    " end_date > TIMESTAMP(CURDATE()) AND due_date < TIMESTAMP(CURDATE())) " +
                    " ORDER BY regularity";
            args = new Object[]{userId};
            tasks = jdbcTemplate.query(sql, new TaskRowMapper(), args);
            return tasks;
        } else {
            sql = "SELECT * FROM tasks WHERE regularity > 0 AND end_date > TIMESTAMP(CURDATE()) " +
                    " AND due_date < TIMESTAMP(CURDATE()) " +
                    " ORDER BY regularity ";
            tasks = jdbcTemplate.query(sql, new TaskRowMapper());
            return tasks;
        }
    }

    @Override
    public List<Task> searchTaskByDescription(String description, Long userId) {
        String sql = "SELECT * FROM tasks WHERE user_id = ? AND task_description LIKE ?";
        Object[] args = new Object[] {userId, "%"+description+"%"}; //adding wildcard to SQL query. for LIKE
        return jdbcTemplate.query(sql, new TaskRowMapper(), args);
    }

    @Override
    public List<Task> searchTaskByDate(LocalDateTime date, Long userId) {
        return null;
    }

    @Override
    public List<Task> searchTaskByDateRange(LocalDateTime start, LocalDateTime end, SearchDateType type) {
        return null;
    }

    @Override
    public List<Task> searchTasks(String searchPhrase, Long userId) {
        String sql = "SELECT * FROM tasks " +
        "WHERE user_id = ? AND (task_description LIKE ? OR " +
                " CONVERT(due_date, CHAR) LIKE ? OR CONVERT(end_date, CHAR) LIKE ?) ";
        Object[] args = new Object[] {userId, "%"+searchPhrase+"%", "%"+searchPhrase+"%",
                                    "%"+searchPhrase+"%"}; //adding wildcard to SQL query. for LIKE
        return jdbcTemplate.query(sql, new TaskRowMapper(), args);
    }

}
