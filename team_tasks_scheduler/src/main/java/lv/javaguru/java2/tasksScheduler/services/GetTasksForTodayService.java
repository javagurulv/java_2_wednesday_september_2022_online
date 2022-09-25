package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;

import java.util.List;

public class GetTasksForTodayService {

    private TasksRepository tasksRepository;
    private SessionService sessionService;

    public GetTasksForTodayService(TasksRepository tasksRepository, SessionService sessionService) {
        this.tasksRepository = tasksRepository;
        this.sessionService = sessionService;
    }

    public List<Task> execute() {
        return tasksRepository.getAllOutstandingTasksByUserIdForToday(sessionService.getCurrentUserId());
    }
}
