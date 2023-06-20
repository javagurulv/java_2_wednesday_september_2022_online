package lv.javaguru.java2.tasksScheduler.core.services.scheduled_jobs;


import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaTasksRepository;
import lv.javaguru.java2.tasksScheduler.core.requests.JobRunRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.JobRunResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.CreateLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@Transactional
public class TasksCleanupService {

    @Value("${logs.job.tasks.cleanup.create}")
    private boolean createLog;

    @Autowired private JpaTasksRepository tasksRepository;
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
