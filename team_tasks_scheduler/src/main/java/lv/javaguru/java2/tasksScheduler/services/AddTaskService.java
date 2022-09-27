package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.AddTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.AddTaskResponse;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;

import java.util.List;

public class AddTaskService {

    private TasksRepository tasksRepository;
    private SessionService sessionService;
    private TaskInfoValidator validator;

    public AddTaskService(TasksRepository tasksRepository, SessionService sessionService,
                          TaskInfoValidator validator) {
        this.tasksRepository = tasksRepository;
        this.sessionService = sessionService;
        this.validator = validator;
    }

    public AddTaskResponse execute(AddTaskRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddTaskResponse(errors);
        }

        Task task = new Task(request.getDescription(), request.getRegularity(), request.getDueDate(),
                                request.getEndDate(), sessionService.getCurrentUserId());
        tasksRepository.save(task);
        //TODO check return code
        return new AddTaskResponse(task);
    }
}
