package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.AddTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TaskInfoValidator {

    public List<CoreError> validate(AddTaskRequest request, TasksRepository taskList,
                                        SessionService session) {
        List<CoreError> errors = new ArrayList<>();

        validateDuplicate(request, taskList, session).ifPresent(errors::add);
        validateDescription(request).ifPresent(errors::add);
        validateRegularity(request).ifPresent(errors::add);
        validateDueDate(request).ifPresent(errors::add);
        validateEndDate(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateDuplicate(AddTaskRequest request,
                                      TasksRepository taskList, SessionService session) {

        Task task = new Task(request.getDescription(), request.getRegularity(),
                request.getDueDate(), request.getEndDate(), session.getCurrentUserId());
        if (taskList.exists(task)) {
            return Optional.of(new CoreError("Task", "Already exists in database"));
        }
        return Optional.empty();
    }
    private Optional<CoreError> validateDescription(AddTaskRequest request) {
        String description = request.getDescription();
        if (description == null || description.isEmpty() ||
                description.length() < 10) {
            return Optional.of(new CoreError("Description", "Has to be longer than 10 chars"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateRegularity(AddTaskRequest request) {
        int regularity = request.getRegularity();
        if (regularity < 0) {
            return Optional.of(new CoreError("Regularity", "Can't be negative"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateDueDate(AddTaskRequest request) {
        LocalDateTime dueDate = request.getDueDate();
        LocalDateTime endDate = request.getEndDate();
        LocalDateTime now = LocalDateTime.now();
        if (dueDate.isBefore(now) || dueDate.isAfter(endDate)) {
            return Optional.of(new CoreError("Due date", "Start date can't be in the past or > End date"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateEndDate(AddTaskRequest request) {
        LocalDateTime endDate = request.getEndDate();
        LocalDateTime dueDate = request.getDueDate();
        LocalDate endDateOnly = LocalDate.of(endDate.getYear(),
                                                    endDate.getMonth(),
                                                    endDate.getDayOfMonth());
        LocalDate dueDateOnly = LocalDate.of(dueDate.getYear(),
                                                     dueDate.getMonth(),
                                                     dueDate.getDayOfMonth());
        Period period = Period.between(dueDateOnly, endDateOnly);
        if (period.getYears() > 100 || endDate.isBefore(LocalDateTime.now())) {
            return Optional.of(new CoreError("End date", "Must not be in the past or > 100 years in the future"));
        }
        return Optional.empty();
    }
}
