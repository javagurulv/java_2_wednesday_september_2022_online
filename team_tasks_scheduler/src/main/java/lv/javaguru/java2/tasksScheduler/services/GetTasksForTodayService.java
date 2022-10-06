package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.GetTasksForTodayRequests;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.GetTaskForTodayResponse;

import java.util.ArrayList;
import java.util.List;

public class GetTasksForTodayService {

    private TasksRepository tasksRepository;
    private SessionService sessionService;

    public GetTasksForTodayService(TasksRepository tasksRepository, SessionService sessionService) {
        this.tasksRepository = tasksRepository;
        this.sessionService = sessionService;
    }

    public GetTaskForTodayResponse execute(GetTasksForTodayRequests request) {
        List<CoreError> errors = new ArrayList<>();
        List<Task> tasks =  tasksRepository.getAllOutstandingTasksByUserIdForToday(sessionService.getCurrentUserId());

        return new GetTaskForTodayResponse(tasks, errors);
    }
}
