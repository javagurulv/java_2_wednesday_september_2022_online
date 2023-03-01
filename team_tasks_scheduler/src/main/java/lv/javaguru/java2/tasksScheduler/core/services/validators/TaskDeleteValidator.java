package lv.javaguru.java2.tasksScheduler.core.services.validators;

import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.requests.AmendTaskRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.DeleteTaskRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TaskDeleteValidator {

    public List<CoreError> validate(DeleteTaskRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validatePositiveIndex(request).ifPresent(errors::add);

        return errors;
    }

    private Optional<CoreError> validatePositiveIndex(DeleteTaskRequest request) {
        Long taskId = request.getTaskId();
        if (taskId < 0) {
            return Optional.of(new CoreError("Task ID", "Invalid value"));
        }
        return Optional.empty();
    }
}
