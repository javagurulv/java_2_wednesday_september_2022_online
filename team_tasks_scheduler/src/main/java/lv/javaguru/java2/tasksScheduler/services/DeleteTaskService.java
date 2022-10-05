package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.requests.DeleteTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.DeleteTaskResponse;

public class DeleteTaskService {

    private TasksRepository tasksRepository;

    public DeleteTaskService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public DeleteTaskResponse execute(DeleteTaskRequest request) {
        tasksRepository.deleteById(request.getTaskId());
        return new DeleteTaskResponse();
    }
}
