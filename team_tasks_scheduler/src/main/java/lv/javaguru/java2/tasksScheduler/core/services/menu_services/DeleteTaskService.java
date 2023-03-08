package lv.javaguru.java2.tasksScheduler.core.services.menu_services;


import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaTasksRepository;
import lv.javaguru.java2.tasksScheduler.core.requests.DeleteTaskRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AmendTaskResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.responses.DeleteTaskResponse;
import lv.javaguru.java2.tasksScheduler.core.services.validators.TaskAmendValidator;
import lv.javaguru.java2.tasksScheduler.core.services.validators.TaskDeleteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteTaskService {
    @Autowired
    private JpaTasksRepository tasksRepository;
    @Autowired
    private TaskDeleteValidator validator;
    public DeleteTaskResponse execute(DeleteTaskRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteTaskResponse(errors);
        }

        tasksRepository.deleteById(request.getTaskId());
        return new DeleteTaskResponse();
    }
}
