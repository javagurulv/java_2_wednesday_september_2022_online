package lv.javaguru.java2.tasksScheduler.core.database.jpa;

import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface JpaTasksCustomRep {

    boolean update(Task task);


    int deleteByUserIdTillDate(Long userId, LocalDateTime endDate);

    boolean exists(Task task);

    List<Task> getAllOutstandingTasksByUserIdTillDate(Long userId, LocalDateTime endDate);

    List<Task> getAllTasksReadyForDueDateUpdate(Long userId);

    List<Task> searchTasks(String searchPhrase, Long userID);

    List<Long> getAllTasksIdsToCleanup(LocalDateTime endDate);
}
