package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;


import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.AddTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.AddTaskResponse;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.UserRegistrationResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.services.validators.TaskInfoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddTaskService {

    @Autowired
    private TasksRepository tasksRepository;
    @Autowired private SessionService sessionService;
    @Autowired private TaskInfoValidator validator;

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
