package lv.javaguru.java2.tasksScheduler.core.services.menu_services;


import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaTasksRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.requests.AddTaskRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AddTaskResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.core.services.validators.TaskAddValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddTaskService {

    @Autowired
    private JpaTasksRepository tasksRepository;
    @Autowired private SessionService sessionService;
    @Autowired private TaskAddValidator validator;

    public AddTaskResponse execute(AddTaskRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddTaskResponse(errors);
        }

        Task task = new Task(request.getDescription(), request.getRegularity(), request.getDueDate(),
                                request.getEndDate(), sessionService.getCurrentUserId());

        var result = tasksRepository.save(task); //TODO small rework for JPA
        if (result.getId() == null) {
            errors.add(new CoreError("General","Error"));
            return new AddTaskResponse(errors);
        }
        
        return new AddTaskResponse(task);
    }
}
