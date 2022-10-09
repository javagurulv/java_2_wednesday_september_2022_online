package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.requests.AmendTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskAmendValidator {
    public List<CoreError> validate(AmendTaskRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateDescription(request).ifPresent(errors::add);
        validateRegularity(request).ifPresent(errors::add);
        validateDueDate(request).ifPresent(errors::add);
        validateEndDate(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateDescription(AmendTaskRequest request) {
        String description = request.getTask().getDescription();
        if (description == null || description.isEmpty() ||
                description.length() < 10) {
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
        if (dueDate.isBefore(now) == true ||
                dueDate.isBefore(endDate) == true) {
            return Optional.of(new CoreError("Due date", "Start date can't be in the past"));
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
        if (period.getYears() > 100) {
            return Optional.of(new CoreError("End date", "Humans don't live that long!"));
        }
        return Optional.empty();
    }
}