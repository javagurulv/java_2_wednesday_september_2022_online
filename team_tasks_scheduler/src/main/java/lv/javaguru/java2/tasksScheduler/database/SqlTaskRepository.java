package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.enums.SearchDateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class SqlTaskRepository implements TasksRepository{

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public boolean save(Task task) {
        int result;

        String sql = "INSERT INTO tasks (task_description, regularity," +
                                                            "Due_date, End_date, user_id) "
                + " VALUES (?, ?, ?, ?, ?)";
        Object[] args = new Object[]{task.getDescription(), task.getRegularity(),
                                    task.getDueDate(), task.getEndDate(), task.getUserId()};
        result = jdbcTemplate.update(sql, args);

        return result == 1;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public int deleteByUserIdTillDate(Long userId, LocalDateTime endDate) {
        return 0;
    }

    @Override
    public boolean update(Task task) {
        return false;
    }

    @Override
    public boolean exists(Task task) {
        return false;
    }

    @Override
    public Task getTaskById(Long id) {
        return null;
    }

    @Override
    public List<Task> getAllOutstandingTasksByUserIdTillDate(Long userId, LocalDateTime endDate) {
        return null;
    }

    @Override
    public List<Task> getAllTasksReadyForDueDateUpdate(Long userId) {
        return null;
    }

    @Override
    public List<Task> searchTaskByDescription(String description) {
        return null;
    }

    @Override
    public List<Task> searchTaskByDate(LocalDateTime date) {
        return null;
    }

    @Override
    public List<Task> searchTaskByDateRange(LocalDateTime start, LocalDateTime end, SearchDateType type) {
        return null;
    }

    @Override
    public List<Task> searchTasks(String searchPhrase, Long userID) {
        return null;
    }
}
