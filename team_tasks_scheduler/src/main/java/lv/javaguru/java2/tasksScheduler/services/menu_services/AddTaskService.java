package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.AddTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.AddTaskResponse;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.UserRegistrationResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.services.validators.TaskInfoValidator;

import java.util.List;

@DIComponent
public class AddTaskService {

    @DIDependency private TasksRepository tasksRepository;
    @DIDependency private SessionService sessionService;
    @DIDependency private TaskInfoValidator validator;

    public AddTaskResponse execute(AddTaskRequest request) {
        List<CoreError> errors = validator.validate(request, tasksRepository, sessionService);
        if (!errors.isEmpty()) {
            return new AddTaskResponse(errors);
        }

        Task task = new Task(request.getDescription(), request.getRegularity(), request.getDueDate(),
                                request.getEndDate(), sessionService.getCurrentUserId());
        tasksRepository.exists(task);

        if(!tasksRepository.save(task)) {
            errors.add(new CoreError("General","Error"));
            return new AddTaskResponse(errors);
        }
        
        return new AddTaskResponse(task);
    }
}
