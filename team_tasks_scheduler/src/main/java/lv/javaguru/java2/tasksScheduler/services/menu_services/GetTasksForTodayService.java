package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;


import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.GetTasksForTodayRequests;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.GetTaskForTodayResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetTasksForTodayService {

    @Autowired
    private TasksRepository tasksRepository;
    @Autowired private SessionService sessionService;

    public GetTaskForTodayResponse execute(GetTasksForTodayRequests request) {
        List<Task> tasks =  tasksRepository.getAllOutstandingTasksByUserIdForToday(sessionService.getCurrentUserId());

        return new GetTaskForTodayResponse(tasks, null);
    }
}
