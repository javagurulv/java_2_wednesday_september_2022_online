package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;


import lv.javaguru.java2.tasksScheduler.requests.DeleteTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.DeleteTaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteTaskService {
    @Autowired
    private TasksRepository tasksRepository;

    public DeleteTaskResponse execute(DeleteTaskRequest request) {
        tasksRepository.deleteById(request.getTaskId());
        return new DeleteTaskResponse();
    }
}
