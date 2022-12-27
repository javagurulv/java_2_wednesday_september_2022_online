package lv.javaguru.java2.tasksScheduler.core.database.jpa;

import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaTasksRepository extends JpaRepository<Task, Long>, JpaTasksCustomRep {

    Task findTaskById(Long TaskId);
}
