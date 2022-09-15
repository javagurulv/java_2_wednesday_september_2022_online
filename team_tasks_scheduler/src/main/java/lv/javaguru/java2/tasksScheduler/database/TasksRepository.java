package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.Task;

import java.util.Date;
import java.util.List;

public interface TasksRepository {

    void save(Task task);

    void deleteById(Long id);

    void deleteByUserId(Long userId);

    void update(Task task);

    boolean exists(Task task);

    List<Task> getAllTasksById(Long id);

    List<Task> getAllTasksByUserId(Long userId);

    List<Task> getAllTasksByUserIdAndDate(Long userId, Date date);
}
