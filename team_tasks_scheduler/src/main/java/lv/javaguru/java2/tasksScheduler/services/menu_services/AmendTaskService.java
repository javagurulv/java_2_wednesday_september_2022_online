package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.requests.AmendTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.AmendTaskResponse;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.services.validators.TaskAmendValidator;

import java.util.List;

@DIComponent
public class AmendTaskService {

    @DIDependency private TasksRepository tasksRepository;
    @DIDependency private TaskAmendValidator validator;

    public AmendTaskResponse execute(AmendTaskRequest request) {
        List<CoreError> errors = validator.validate(request, tasksRepository);
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
