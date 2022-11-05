package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.services.system.CreateLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public class RemindersSendingRunService {
    @Value("${logs.job.tasks.reminderssending.create}")
    private boolean createLog;

    @Value("${reminder.email.send}")
    private boolean sendReminders;

    @Autowired
    private RemindersSendingService remindersSendingService;
    @Autowired private CreateLogsService createLogsService;

    public JobRunResult run(boolean manual) {
        if (!sendReminders) {
            return null;
        }
        JobRunResult result = new JobRunResult("RemindersSending");
        if (!manual) {
            result.setRunType("Auto");
        }
        try {
            result.setActionsCount(remindersSendingService.execute());
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
