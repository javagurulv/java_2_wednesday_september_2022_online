package lv.javaguru.java2.tasksScheduler.core.services.menu_services;


import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaTasksRepository;
import lv.javaguru.java2.tasksScheduler.core.requests.AmendTaskRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AmendTaskResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.services.validators.TaskAmendValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AmendTaskService {

    @Autowired
    private JpaTasksRepository tasksRepository;
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
