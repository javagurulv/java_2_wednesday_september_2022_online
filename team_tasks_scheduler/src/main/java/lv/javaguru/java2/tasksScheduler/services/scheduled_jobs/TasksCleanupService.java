package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;


import lv.javaguru.java2.tasksScheduler.requests.JobRunRequest;
import lv.javaguru.java2.tasksScheduler.responses.JobRunResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TasksCleanupService implements Runnable{
    @Autowired
    TasksCleanupRunService tasksCleanupRunService;
    @Override
    public void run() {
        System.out.println("Automatic task deletion");
        JobRunRequest request = new JobRunRequest(false);
        JobRunResponse response = tasksCleanupRunService.execute(request);
    }
}
