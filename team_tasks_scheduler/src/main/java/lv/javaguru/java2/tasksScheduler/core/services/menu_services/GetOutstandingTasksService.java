package lv.javaguru.java2.tasksScheduler.core.services.menu_services;

import lv.javaguru.java2.tasksScheduler.core.database.TasksRepository;


import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.requests.GetOutstandingTasksRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.GetOutstandingTasksResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetOutstandingTasksService {

    @Autowired
    private TasksRepository tasksRepository;
    @Autowired private SessionService sessionService;

    public GetOutstandingTasksResponse execute(GetOutstandingTasksRequest request) {
        List<Task> tasks =  tasksRepository.getAllOutstandingTasksByUserIdTillDate(sessionService.getCurrentUserId(),
                request.getEndDate());

        return new GetOutstandingTasksResponse(tasks, null);
    }
}
