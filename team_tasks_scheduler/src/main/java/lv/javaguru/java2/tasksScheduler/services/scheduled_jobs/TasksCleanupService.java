package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;

@DIComponent
public class TasksCleanupService {

    @DIDependency private TasksRepository tasksRepository;

    public void execute() {
        tasksRepository.deleteOutOfDate();
    }
}
