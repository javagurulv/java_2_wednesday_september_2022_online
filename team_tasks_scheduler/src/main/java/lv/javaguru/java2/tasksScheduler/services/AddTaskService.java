package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;

import java.time.LocalDateTime;

public class AddTaskService {

    private TasksRepository tasksRepository;
    private SessionService sessionService;

    public AddTaskService(TasksRepository tasksRepository, SessionService sessionService) {
        this.tasksRepository = tasksRepository;
        this.sessionService = sessionService;
    }

    public boolean execute(String description, int regularity, LocalDateTime dueDate, LocalDateTime  endDate) {
        Task task = new Task(description, regularity, dueDate, endDate, sessionService.getCurrentUserId());
        return tasksRepository.save(task);
    }
}
