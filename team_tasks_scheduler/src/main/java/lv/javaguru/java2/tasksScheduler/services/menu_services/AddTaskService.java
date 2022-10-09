package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.AddTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.AddTaskResponse;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.UserRegistrationResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.services.validators.TaskInfoValidator;

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
        if(!tasksRepository.save(task)) {
            errors.add(new CoreError("General","Error"));
            return new AddTaskResponse(errors);
        }
        
        return new AddTaskResponse(task);
    }
}
