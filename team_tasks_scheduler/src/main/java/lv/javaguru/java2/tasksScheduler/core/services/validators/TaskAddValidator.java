package lv.javaguru.java2.tasksScheduler.core.services.validators;


import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaTasksRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.requests.AddTaskRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TaskAddValidator {
    @Autowired private JpaTasksRepository tasksRepository;
    @Autowired private SessionService sessionService;

    public List<CoreError> validate(AddTaskRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateDuplicate(request).ifPresent(errors::add);
        validateDescription(request).ifPresent(errors::add);
        validateRegularity(request).ifPresent(errors::add);
        validateDueDate(request).ifPresent(errors::add);
        validateEndDate(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateDuplicate(AddTaskRequest request) {

        Task task = new Task(request.getDescription(), request.getRegularity(),
                request.getDueDate(), request.getEndDate(), sessionService.getCurrentUserId());
        if (tasksRepository.exists(task)) {
            return Optional.of(new CoreError("Task", "Already exists in database!"));
        }
        return Optional.empty();
    }
    private Optional<CoreError> validateDescription(AddTaskRequest request) {
        String description = request.getDescription();
        if (ValueChecking.stringIsEmpty(description) ||
                description.length() < 10) {
            return Optional.of(new CoreError("Description", "Has to be longer than 9 chars!"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateRegularity(AddTaskRequest request) {
        int regularity = request.getRegularity();
        if (regularity < 0) {
            return Optional.of(new CoreError("Regularity", "Can't be negative!"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateDueDate(AddTaskRequest request) {
        LocalDateTime dueDate = request.getDueDate();
        LocalDateTime endDate = request.getEndDate();
        LocalDateTime now = LocalDateTime.now();
        if (dueDate.isBefore(now) || dueDate.isAfter(endDate)) {
            return Optional.of(new CoreError("Due date", "Start date can't be in the past or > End date!"));
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
            return Optional.of(new CoreError("End date", "Must not be in the past or > 100 years in the future!"));
        }
        return Optional.empty();
    }
}
