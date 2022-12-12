package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.core.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.requests.AddTaskRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.core.services.validators.TaskAddValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskAddValidatorTest {

    @Mock private TasksRepository tasksRepository;
    @Mock private SessionService sessionService;
    @InjectMocks private TaskAddValidator validator;

    @Test
    public void shouldReturnErrorWhenDuplicateTask() {
        AddTaskRequest request = new AddTaskRequest("duplicate task", 0,
                LocalDateTime.of(2123,5,12,0,0),
                LocalDateTime.of(2123,7,12,0,0));
        when(sessionService.getCurrentUserId()).thenReturn(1L);
        Task task = new Task(request.getDescription(), request.getRegularity(),
                request.getDueDate(), request.getEndDate(), 1L);
        when(tasksRepository.exists(task)).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Task");
        assertEquals(errors.get(0).getMessage(), "Already exists in database!");
    }

    @Test
    public void shouldSuccessWhenNonDuplicateTask() {
        AddTaskRequest request = new AddTaskRequest("non-duplicate task", 0,
                LocalDateTime.of(2123,5,12,0,0),
                LocalDateTime.of(2123,7,12,0,0));
        when(sessionService.getCurrentUserId()).thenReturn(1L);
        Task task = new Task(request.getDescription(), request.getRegularity(),
                request.getDueDate(), request.getEndDate(), 1L);
        when(tasksRepository.exists(task)).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenDescriptionIsNull() {
        AddTaskRequest request = new AddTaskRequest(null, 0,
                LocalDateTime.of(2123,5,12,0,0),
                LocalDateTime.of(2123,7,12,0,0));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Description");
        assertEquals(errors.get(0).getMessage(), "Has to be longer than 9 chars!");
    }

    @Test
    public void shouldReturnErrorWhenDescriptionIsEmpty() {
        AddTaskRequest request = new AddTaskRequest(" ", 0,
                LocalDateTime.of(2123,5,12,0,0),
                LocalDateTime.of(2123,7,12,0,0));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Description");
        assertEquals(errors.get(0).getMessage(), "Has to be longer than 9 chars!");
    }

    @Test
    public void shouldReturnErrorWhenDescriptionHasWrongLength() {
        AddTaskRequest request = new AddTaskRequest("123456789", 0,
                LocalDateTime.of(2123,5,12,0,0),
                LocalDateTime.of(2123,7,12,0,0));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Description");
        assertEquals(errors.get(0).getMessage(), "Has to be longer than 9 chars!");
    }

    @Test
    public void shouldSuccessWhenDescriptionLengthIsValid() {
        AddTaskRequest request = new AddTaskRequest("1234567890", 0,
                LocalDateTime.of(2123,5,12,0,0),
                LocalDateTime.of(2123,7,12,0,0));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenRegularityIsLessThanZero() {
        AddTaskRequest request = new AddTaskRequest("Task description", -1,
                LocalDateTime.of(2123,5,12,0,0),
                LocalDateTime.of(2123,7,12,0,0));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Regularity");
        assertEquals(errors.get(0).getMessage(), "Can't be negative!");
    }

    @Test
    public void shouldSuccessWhenRegularityIsNotLessThanZero() {
        AddTaskRequest request = new AddTaskRequest("Task description", 0,
                LocalDateTime.of(2123,5,12,0,0),
                LocalDateTime.of(2123,7,12,0,0));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenDueDateInThePast() {
        AddTaskRequest request = new AddTaskRequest("Task description", 0,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.of(2123,7,12,0,0));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Due date");
        assertEquals(errors.get(0).getMessage(), "Start date can't be in the past or > End date!");
    }

    @Test
    public void shouldReturnErrorWhenDueDateIsMoreThanEndDate() {
        AddTaskRequest request = new AddTaskRequest("Task description", 0,
                LocalDateTime.now().plusDays(4), LocalDateTime.now().plusDays(2).minusDays(1));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Due date");
        assertEquals(errors.get(0).getMessage(), "Start date can't be in the past or > End date!");
    }

    @Test
    public void shouldSuccessWhenDueDateIsValid() {
        AddTaskRequest request = new AddTaskRequest("Task description", 0,
                LocalDateTime.of(2123,5,12,0,0),
                LocalDateTime.of(2123,7,12,0,0));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturn2ErrorsWhenEndDateInThePast() {
        AddTaskRequest request = new AddTaskRequest("Task description", 0,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().minusDays(1));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
    }

    @Test
    public void shouldReturnErrorWhenEndDateIsVeryFarInTheFuture() {
        AddTaskRequest request = new AddTaskRequest("Task description", 0,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusYears(102));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "End date");
        assertEquals(errors.get(0).getMessage(), "Must not be in the past or > 100 years in the future!");
    }

    @Test
    public void shouldSuccessWhenEndDateIsValid() {
        AddTaskRequest request = new AddTaskRequest("Task description", 0,
                LocalDateTime.of(2123,5,12,0,0),
                LocalDateTime.of(2123,7,12,0,0));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}