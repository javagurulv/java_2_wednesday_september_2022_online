package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.GetOutstandingTasksRequests;
import lv.javaguru.java2.tasksScheduler.responses.GetOutstandingTasksResponse;

import java.util.List;

public class GetOutstandingTasksService {

    private TasksRepository tasksRepository;
    private SessionService sessionService;

    public GetOutstandingTasksService(TasksRepository tasksRepository, SessionService sessionService) {
        this.tasksRepository = tasksRepository;
        this.sessionService = sessionService;
    }

    public GetOutstandingTasksResponse execute(GetOutstandingTasksRequests request) {
        List<Task> tasks =  tasksRepository.getAllOutstandingTasksByUserId(sessionService.getCurrentUserId());

        return new GetOutstandingTasksResponse(tasks, 0);
    }
}
