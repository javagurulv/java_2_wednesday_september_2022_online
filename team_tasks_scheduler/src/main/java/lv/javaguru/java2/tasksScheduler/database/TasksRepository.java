package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.Task;

import java.util.List;

public interface TasksRepository {

    boolean save(Task task);

    void deleteById(Long id);

    void deleteByUserId(Long userId);

    void deleteOutOfDateByUserId(Long userId);

    boolean update(Task task);

    boolean exists(Task task);

    Task getTaskById(Long id);

    List<Task> getAllOutstandingTasksByUserId(Long userId);

    List<Task> getAllOutstandingTasksByUserIdForToday(Long userId);
}
