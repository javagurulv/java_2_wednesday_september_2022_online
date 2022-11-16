package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;


import lv.javaguru.java2.tasksScheduler.requests.JobRunRequest;
import lv.javaguru.java2.tasksScheduler.responses.JobRunResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TasksCleanupJob implements Runnable {
    @Autowired
    TasksCleanupService tasksCleanupService;
    @Override
    public void run() {
        JobRunRequest request = new JobRunRequest(false);
        JobRunResponse response = tasksCleanupService.execute(request);
    }
}
