package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.AmendTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TaskAmendValidator {
    @Autowired private TasksRepository tasksRepository;

    public List<CoreError> validate(AmendTaskRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateTaskExpired(request).ifPresent(errors::add);
        validateDuplicate(request).ifPresent(errors::add);
        validateDescription(request).ifPresent(errors::add);
        validateRegularity(request).ifPresent(errors::add);
        validateDueDate(request).ifPresent(errors::add);
        validateEndDate(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateTaskExpired(AmendTaskRequest request) {
        Task currentTask = tasksRepository.getTaskById(request.getTask().getId());
        if (currentTask != null && currentTask.getEndDate().isBefore(LocalDateTime.now())) {
            return Optional.of(new CoreError("Task", "Already expired, please create new"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateDuplicate(AmendTaskRequest request) {
        Task amendedTask = request.getTask();
        if (tasksRepository.exists(amendedTask)) {
            return Optional.of(new CoreError("Task", "Already exists"));
        }
        return Optional.empty();
    }
    private Optional<CoreError> validateDescription(AmendTaskRequest request) {
        String description = request.getTask().getDescription();
        if (description == null || description.length() < 10) {
            return Optional.of(new CoreError("Description", "Has to be longer than 10 chars"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateRegularity(AmendTaskRequest request) {
        int regularity = request.getTask().getRegularity();
        if (regularity < 0) {
            return Optional.of(new CoreError("Regularity", "Can't be negative"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateDueDate(AmendTaskRequest request) {
        LocalDateTime dueDate = request.getTask().getDueDate();
        LocalDateTime endDate = request.getTask().getEndDate();
        LocalDateTime now = LocalDateTime.now();
        if (dueDate.isBefore(now) || dueDate.isAfter(endDate)) {
            return Optional.of(new CoreError("Due date", "Start date can't be in the past or > End date"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateEndDate(AmendTaskRequest request) {
        LocalDateTime endDate = request.getTask().getEndDate();
        LocalDateTime dueDate = request.getTask().getDueDate();
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
