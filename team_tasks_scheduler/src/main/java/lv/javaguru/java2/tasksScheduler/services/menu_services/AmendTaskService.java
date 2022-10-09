package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.requests.AmendTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.AmendTaskResponse;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.services.validators.TaskAmendValidator;

import java.util.List;

public class AmendTaskService {

    private TasksRepository tasksRepository;
    private TaskAmendValidator validator;

    public AmendTaskService(TasksRepository tasksRepository, TaskAmendValidator validator) {
        this.tasksRepository = tasksRepository;
        this.validator = validator;
    }

    public AmendTaskResponse execute(AmendTaskRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AmendTaskResponse(errors);
        }

        if (!tasksRepository.update(request.getTask())) {
            errors.clear();
            errors.add(new CoreError("Database", "Unexpected error"));
            return new AmendTaskResponse(errors);
        }

        return new AmendTaskResponse(request.getTask());
    }
}
