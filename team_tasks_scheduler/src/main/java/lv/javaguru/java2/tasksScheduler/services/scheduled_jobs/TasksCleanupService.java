package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;

public class TasksCleanupService {

    private TasksRepository tasksRepository;

    public TasksCleanupService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public void execute() {
        tasksRepository.deleteOutOfDate();
    }
}
