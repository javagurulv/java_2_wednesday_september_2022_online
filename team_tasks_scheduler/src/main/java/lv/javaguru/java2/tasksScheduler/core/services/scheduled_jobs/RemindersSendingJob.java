package lv.javaguru.java2.tasksScheduler.core.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.core.requests.JobRunRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.JobRunResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemindersSendingJob implements Runnable {
    @Autowired RemindersSendingService remindersSendingService;
    @Override
    public void run() {
        JobRunRequest request = new JobRunRequest(false);
        JobRunResponse response = remindersSendingService.execute(request);
    }
}

