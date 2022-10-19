package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.requests.DeleteTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.DeleteTaskResponse;

@DIComponent
public class DeleteTaskService {
    @DIDependency private TasksRepository tasksRepository;

    public DeleteTaskResponse execute(DeleteTaskRequest request) {
        tasksRepository.deleteById(request.getTaskId());
        return new DeleteTaskResponse();
    }
}
