package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.AmendTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
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
public class TaskAmendValidatorTest {

    @Mock private TasksRepository tasksRepository;
    @InjectMocks private TaskAmendValidator validator;

    @Test
    public void shouldReturnErrorWhenTaskExpired() {
        Task taskInRepo = new Task("task description", 0, LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(1), 1L);
        taskInRepo.setId(1L);
        when(tasksRepository.getTaskById(taskInRepo.getId())).thenReturn(taskInRepo);
        Task task = new Task("task description", 0, LocalDateTime.now().plusMinutes(1),
                LocalDateTime.now().plusMinutes(2), 1L);
        task.setId(1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Task");
        assertEquals(errors.get(0).getMessage(), "Already expired, please create new!");
    }

    @Test
    public void shouldSuccessWhenTaskIsNotExpired() {
        Task task = new Task("task description", 0, LocalDateTime.now().plusMinutes(1),
                LocalDateTime.now().plusMinutes(2), 1L);
        task.setId(1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        when(tasksRepository.getTaskById(request.getTask().getId())).thenReturn(task);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenDuplicateTask() {
        Task task = new Task("duplicate task", 0, LocalDateTime.now().plusDays(5),
                LocalDateTime.now().plusDays(10), 1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        when(tasksRepository.exists(task)).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Task");
        assertEquals(errors.get(0).getMessage(), "Already exists!");
    }

    @Test
    public void shouldSuccessWhenNonDuplicateTask() {
        Task task = new Task("non-duplicate task", 0, LocalDateTime.now().plusDays(5),
                LocalDateTime.now().plusDays(10), 1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        when(tasksRepository.exists(task)).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenDescriptionIsNull() {
        Task task = new Task(null, 0, LocalDateTime.now().plusDays(5),
                LocalDateTime.now().plusDays(10), 1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Description");
        assertEquals(errors.get(0).getMessage(), "Has to be longer than 9 chars!");
    }

    @Test
    public void shouldReturnErrorWhenDescriptionIsEmpty() {
        Task task = new Task(" ", 0, LocalDateTime.now().plusDays(5),
                LocalDateTime.now().plusDays(10), 1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Description");
        assertEquals(errors.get(0).getMessage(), "Has to be longer than 9 chars!");
    }

    @Test
    public void shouldReturnErrorWhenDescriptionHasWrongLength() {
        Task task = new Task("123456789", 0, LocalDateTime.now().plusDays(5),
                LocalDateTime.now().plusDays(10), 1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Description");
        assertEquals(errors.get(0).getMessage(), "Has to be longer than 9 chars!");
    }

    @Test
    public void shouldSuccessWhenDescriptionLengthIsValid() {
        Task task = new Task("1234567890", 0, LocalDateTime.now().plusDays(5),
                LocalDateTime.now().plusDays(10), 1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenRegularityIsLessThanZero() {
        Task task = new Task("Task description", -1, LocalDateTime.now().plusDays(5),
                LocalDateTime.now().plusDays(10), 1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Regularity");
        assertEquals(errors.get(0).getMessage(), "Can't be negative!");
    }

    @Test
    public void shouldSuccessWhenRegularityIsNotLessThanZero() {
        Task task = new Task("Task description", 0, LocalDateTime.now().plusDays(5),
                LocalDateTime.now().plusDays(10), 1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenDueDateInThePast() {
        Task task = new Task("Task description", 0, LocalDateTime.now().minusMinutes(1),
                LocalDateTime.now().plusMinutes(1), 1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Due date");
        assertEquals(errors.get(0).getMessage(), "Start date can't be in the past or > End date!");
    }

    @Test
    public void shouldReturnErrorWhenDueDateIsMoreThanEndDate() {
        Task task = new Task("Task description", 0,   LocalDateTime.now().plusDays(4),
                LocalDateTime.now().plusDays(2).minusDays(1), 1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Due date");
        assertEquals(errors.get(0).getMessage(), "Start date can't be in the past or > End date!");
    }

    @Test
    public void shouldSuccessWhenDueDateIsValid() {
        Task task = new Task("Task description", 0,   LocalDateTime.now().plusMinutes(1),
                LocalDateTime.now().plusMinutes(10), 1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturn2ErrorsWhenEndDateInThePast() {
        Task task = new Task("Task description", 0,   LocalDateTime.now().plusMinutes(1),
                LocalDateTime.now().minusMinutes(1), 1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
    }

    @Test
    public void shouldReturnErrorWhenEndDateIsVeryFarInTheFuture() {
        Task task = new Task("Task description", 0,   LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusYears(102), 1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "End date");
        assertEquals(errors.get(0).getMessage(), "Must not be in the past or > 100 years in the future!");
    }

    @Test
    public void shouldSuccessWhenEndDateIsValid() {
        Task task = new Task("Task description", 0,   LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusYears(1), 1L);
        AmendTaskRequest request = new AmendTaskRequest(task);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}