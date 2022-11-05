package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class DueDatesUpdateService {

    @Autowired private TasksRepository tasksRepository;

    public int execute() {
        List<Task> tasks = tasksRepository.getAllTasksReadyForDueDateUpdate();
        if (tasks == null) {
            return 0;
        }
        int recordsUpdated = 0;
        for (Task task : tasks) {
            do {
                task.setDueDate(task.getDueDate().plusDays(task.getRegularity()));
                if (task.getDueDate().isAfter(LocalDateTime.now().minusDays(1).with(LocalTime.MAX))) {
                    break;
                }
            } while(true);
            if (tasksRepository.update(task)) {
                recordsUpdated++;
            }
        }
        return recordsUpdated;
    }
}
