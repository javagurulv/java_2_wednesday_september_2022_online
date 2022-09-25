package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;

import java.time.LocalDateTime;

public class AddTaskService {

    private TasksRepository tasksRepository;

    public AddTaskService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }


    public void execute( String description, int regularity, LocalDateTime dueDate,
                                                LocalDateTime endDate, Long userId) {

        Task task = new Task(description, regularity, dueDate, endDate, userId);
        tasksRepository.save(task);
    }
}
