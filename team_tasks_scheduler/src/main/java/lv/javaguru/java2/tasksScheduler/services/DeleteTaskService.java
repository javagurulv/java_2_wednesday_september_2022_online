package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;

public class DeleteTaskService {

    private TasksRepository tasksRepository;

    public DeleteTaskService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public void execute(Long taskId) {
        tasksRepository.deleteById(taskId);
    }
}
