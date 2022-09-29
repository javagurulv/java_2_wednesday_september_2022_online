package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.requests.AddTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskInfoValidator {

    public List<CoreError> validate(AddTaskRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateDescription(request).ifPresent(errors::add);
        validateRegularity(request).ifPresent(errors::add);
        validateDueDate(request).ifPresent(errors::add);
        validateEndDate(request).ifPresent(errors::add);
        return errors;
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
        if (dueDate.isBefore(now) == true ||
            dueDate.isBefore(endDate) == true) {
            return Optional.of(new CoreError("Due date", "Start date can't be in the past"));
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
        if (period.getYears() > 100) {
            return Optional.of(new CoreError("End date", "Humans don't live that long!"));
        }
        return Optional.empty();
    }
}
