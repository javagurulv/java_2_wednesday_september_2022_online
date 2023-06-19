package lv.javaguru.java2.tasksScheduler.core.services.scheduled_jobs;


import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaTasksRepository;
import lv.javaguru.java2.tasksScheduler.core.requests.JobRunRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.JobRunResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.CreateLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
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
            List<Long> ids = tasksRepository.getAllTasksIdsToCleanup(LocalDateTime.now().minusDays(1));
            result.setActionsCount(deleteTasksByIds(ids));
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

    private int deleteTasksByIds(List<Long> ids) {
        if (ids == null || ids.size() == 0) {
            return 0;
        }
        int result = 0;
        for (Long id : ids) {
            tasksRepository.deleteById(id);
            result++;
        }
        return result;
    }
}
