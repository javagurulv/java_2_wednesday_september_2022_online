package lv.javaguru.java2.tasksScheduler.core.services.scheduled_jobs;


import lv.javaguru.java2.tasksScheduler.core.requests.JobRunRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.JobRunResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TasksCleanupJob implements Runnable {
    @Autowired
    TasksCleanupService tasksCleanupService;
    @Override
    public void run() {
        System.out.println("sending request for cleanup");
        JobRunRequest request = new JobRunRequest(false);
        JobRunResponse response = tasksCleanupService.execute(request);
    }
}
