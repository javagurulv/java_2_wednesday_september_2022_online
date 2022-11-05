package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.services.system.CreateLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DueDatesUpdateRunService {
    @Value("${logs.job.tasks.duedatesupdate.create}")
    private boolean createLog;

    @Autowired
    private DueDatesUpdateService dueDatesUpdateService;
    @Autowired private CreateLogsService createLogsService;

    public JobRunResult execute(boolean manual) {
        JobRunResult result = new JobRunResult("DueDatesUpdate");
        if (!manual) {
            result.setRunType("Auto");
        }
        try {
            result.setActionsCount(dueDatesUpdateService.execute());
            result.setTimestampEnd(LocalDateTime.now());
            result.setStatus("Succeed");
        } catch (Exception e) {
            return result;
        }
        if (createLog) {
            createLogsService.run(result.getRecordInLogFormat());
        }
        return result;
    }
}
