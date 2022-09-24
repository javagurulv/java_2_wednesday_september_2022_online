package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.Task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class InMemoryTasksRepositoryImpl implements TasksRepository {

    private Long nextId = 1L;
    private List<Task> tasks = new ArrayList<>();

    @Override
    public void save(Task task) {
        if (task == null)
            return;
        task.setId(nextId);
        nextId++;
        tasks.add(task);
        if (nextId == Long.MAX_VALUE)
            nextId = 1L;
    }

    @Override
    public void deleteById(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    @Override
    public void deleteByUserId(Long userId) {
        tasks.removeIf(task -> task.getUserId().equals(userId));
    }

    @Override
    public void deleteOutOfDateByUserId(Long userId) {
        tasks.removeIf(task -> task.getUserId().equals(userId) &&
                task.getEndDate().isBefore(LocalDateTime.now()));
    }

    @Override
    public void update(Task task) {
        if (task == null)
            return;
        if (getTaskById(task.getId()) == null)
            return;
        deleteById(task.getId());
        tasks.add(task);
    }

    @Override
    public boolean exists(Task task) {
        for (Task t : tasks) {
            if (duplicateTasks(t, task))
                return true;
        }
        return false;
    }

    @Override
    public Task getTaskById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Task> getAllOutstandingTasksByUserId(Long userId) {
        return tasks.stream()
                .filter(task -> task.getUserId().equals(userId) &&
                        task.getEndDate().isAfter(LocalDateTime.now()))
                .collect(toList());
    }

    @Override
    public List<Task> getAllOutstandingTasksByUserIdForToday(Long userId) {
        return tasks.stream()
                .filter(task -> task.getUserId().equals(userId) &&
                        task.getEndDate().isAfter(LocalDateTime.now()) &&
                        task.getDueDate().isBefore(LocalDateTime.now().plusDays(1).with(LocalTime.MIN)))
                .collect(toList());
    }

    private boolean duplicateTasks(Task task1, Task task2) {
        if (task1.getRegularity() == task2.getRegularity() &&
                Objects.equals(task1.getDescription(), task2.getDescription()) &&
                Objects.equals(task1.getDueDate(), task2.getDueDate()) &&
                Objects.equals(task1.getEndDate(), task2.getEndDate()) &&
                Objects.equals(task1.getUserId(), task2.getUserId())) {
            return true;
        }
        return false;
    }
}
