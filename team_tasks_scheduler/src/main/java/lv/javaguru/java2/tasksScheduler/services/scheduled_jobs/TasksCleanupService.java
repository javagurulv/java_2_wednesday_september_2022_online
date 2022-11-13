package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.requests.JobRunRequest;
import lv.javaguru.java2.tasksScheduler.responses.JobRunResponse;
import lv.javaguru.java2.tasksScheduler.services.system.CreateLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class TasksCleanupService {

    @Value("${logs.job.tasks.cleanup.create}")
    private boolean createLog;

    @Autowired private TasksRepository tasksRepository;
    @Autowired private CreateLogsService createLogsService;

    public JobRunResponse execute(JobRunRequest request) {
        JobRunResult result = new JobRunResult("TasksCleanup");
        if (!request.isManual()) {
            result.setRunType("Auto");
        }
        try {
            result.setActionsCount(tasksRepository.deleteByUserIdTillDate(null,
                                            LocalDateTime.now().minusDays(1).with(LocalTime.MIN)));
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
}
