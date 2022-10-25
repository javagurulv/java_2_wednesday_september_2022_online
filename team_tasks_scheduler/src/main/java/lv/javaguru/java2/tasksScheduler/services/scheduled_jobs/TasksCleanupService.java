package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Component
public class TasksCleanupService {

    @Autowired private TasksRepository tasksRepository;

    public void execute() {
        tasksRepository.deleteByUserIdTillDate(null, LocalDateTime.now().minusDays(1).with(LocalTime.MIN));
    }
}
