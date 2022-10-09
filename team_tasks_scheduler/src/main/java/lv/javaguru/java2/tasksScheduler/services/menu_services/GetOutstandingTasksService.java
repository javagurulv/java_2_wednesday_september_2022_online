package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.GetOutstandingTasksRequests;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.GetOutstandingTasksResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;

import java.util.ArrayList;
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

        return new GetOutstandingTasksResponse(tasks, null);
    }
}
