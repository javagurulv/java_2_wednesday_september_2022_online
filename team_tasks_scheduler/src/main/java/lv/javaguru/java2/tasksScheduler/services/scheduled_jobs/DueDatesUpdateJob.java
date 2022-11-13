package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.JobRunRequest;
import lv.javaguru.java2.tasksScheduler.responses.JobRunResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class DueDatesUpdateJob implements Runnable {

    @Autowired DueDatesUpdateService dueDatesUpdateService;

    @Override
    public void run() {
        JobRunRequest request = new JobRunRequest(false);
        JobRunResponse response = dueDatesUpdateService.execute(request);
    }
}
