package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;

public class AmendTaskService {

    private TasksRepository tasksRepository;

    public AmendTaskService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public boolean execute(Task amendedTask) {
        return tasksRepository.update(amendedTask);
    }
}
