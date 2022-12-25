package lv.javaguru.java2.tasksScheduler.core.database.jpa;

import lv.javaguru.java2.tasksScheduler.core.domain.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaTasksCustomRep {

    boolean update(Task task);


    int deleteByUserIdTillDate(Long userId, LocalDateTime endDate);

    boolean exists(Task task);

    List<Task> getAllOutstandingTasksByUserIdTillDate(Long userId, LocalDateTime endDate);

    List<Task> getAllTasksReadyForDueDateUpdate(Long userId);

    List<Task> searchTasks(String searchPhrase, Long userID);
}
