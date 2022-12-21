package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.requests.JobRunRequest;
import lv.javaguru.java2.tasksScheduler.responses.JobRunResponse;
import lv.javaguru.java2.tasksScheduler.services.system.CreateLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RemindersSendingRunService {
    @Value("${logs.job.tasks.reminderssending.create}")
    private boolean createLog;

    @Value("${reminder.email.send}")
    private boolean sendReminders;

    @Autowired
    private RemindersSendingService remindersSendingService;
    @Autowired private CreateLogsService createLogsService;

    public JobRunResponse execute(JobRunRequest request) {
        if (!sendReminders) {
            return null;
        }
        JobRunResult result = new JobRunResult("RemindersSending");
        if (!request.isManual()) {
            result.setRunType("Auto");
        }
        try {
            result.setActionsCount(remindersSendingService.execute());
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
