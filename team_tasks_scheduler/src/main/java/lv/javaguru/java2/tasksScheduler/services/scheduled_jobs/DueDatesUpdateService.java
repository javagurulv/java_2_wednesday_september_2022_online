package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.JobRunRequest;
import lv.javaguru.java2.tasksScheduler.responses.JobRunResponse;
import lv.javaguru.java2.tasksScheduler.services.system.CreateLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class DueDatesUpdateService {
    @Value("${logs.job.tasks.duedatesupdate.create}")
    private boolean createLog;

    @Autowired private TasksRepository tasksRepository;
    @Autowired private CreateLogsService createLogsService;

    public JobRunResponse execute(JobRunRequest request) {
        JobRunResult result = new JobRunResult("DueDatesUpdate");
        if (!request.isManual()) {
            result.setRunType("Auto");
        }
        try {
            result.setActionsCount(updateDates());
            result.setTimestampEnd(LocalDateTime.now());
            result.setStatus("Succeed");
        } catch (Exception e) {
            return new JobRunResponse(result);
        }
        if (createLog) {
            createLogsService.execute(result.getRecordInLogFormat());
        }
        return new JobRunResponse(result);
    }

    private int updateDates() {
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
