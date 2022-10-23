package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;


import lv.javaguru.java2.tasksScheduler.requests.AmendTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.AmendTaskResponse;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.services.validators.TaskAmendValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AmendTaskService {

    @Autowired
    private TasksRepository tasksRepository;
    @Autowired private TaskAmendValidator validator;

    public AmendTaskResponse execute(AmendTaskRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AmendTaskResponse(errors);
        }

        if (!tasksRepository.update(request.getTask())) {
            errors.clear();
            errors.add(new CoreError("Tasks repository", "Update failed."));
            return new AmendTaskResponse(errors);
        }

        return new AmendTaskResponse(request.getTask());
    }
}
