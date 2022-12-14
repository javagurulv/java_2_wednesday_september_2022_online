package lv.javaguru.java2.tasksScheduler.core.database;

import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.enums.SearchDateType;

import java.time.LocalDateTime;
import java.util.List;

public interface TasksRepository {

    boolean save(Task task);

    boolean deleteById(Long id);

    int deleteByUserIdTillDate(Long userId, LocalDateTime endDate);

    boolean update(Task task);

    boolean exists(Task task);

    Task getTaskById(Long id);

    List<Task> getAllOutstandingTasksByUserIdTillDate(Long userId, LocalDateTime endDate);

    List<Task> getAllTasksReadyForDueDateUpdate(Long userId);

    List<Task> searchTaskByDescription(String description, Long userId);
    List<Task> searchTaskByDate(LocalDateTime date, Long userId);
    List<Task> searchTaskByDateRange(LocalDateTime start, LocalDateTime end, SearchDateType type);

    List<Task> searchTasks(String searchPhrase, Long userID);
}
