package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.JobRunRequest;
import lv.javaguru.java2.tasksScheduler.responses.JobRunResponse;
import lv.javaguru.java2.tasksScheduler.services.system.CreateLogsService;
import lv.javaguru.java2.tasksScheduler.services.system.ReminderEmailTemplateCreationService;
import lv.javaguru.java2.tasksScheduler.utils.Email;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class RemindersSendingJob implements Runnable {
    @Autowired RemindersSendingService remindersSendingService;
    @Override
    public void run() {
        JobRunRequest request = new JobRunRequest(false);
        JobRunResponse response = remindersSendingService.execute(request);
    }
}

