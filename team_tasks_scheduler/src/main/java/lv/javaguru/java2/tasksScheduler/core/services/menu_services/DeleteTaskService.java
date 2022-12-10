package lv.javaguru.java2.tasksScheduler.core.services.menu_services;

import lv.javaguru.java2.tasksScheduler.core.database.TasksRepository;


import lv.javaguru.java2.tasksScheduler.core.requests.DeleteTaskRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.DeleteTaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DeleteTaskService {
    @Autowired
    private TasksRepository tasksRepository;

    public DeleteTaskResponse execute(DeleteTaskRequest request) {
        tasksRepository.deleteById(request.getTaskId());
        return new DeleteTaskResponse();
    }
}
