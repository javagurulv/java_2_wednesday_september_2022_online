package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.enums.SearchDateType;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class InMemoryTasksRepositoryImpl implements TasksRepository {

    private Long nextId = 1L;
    private List<Task> tasks = new ArrayList<>();

    @Override
    public boolean save(Task task) {
        if (task == null)
            return false;
        task.setId(nextId);
        nextId++;
        tasks.add(task);
        if (nextId == Long.MAX_VALUE)
            nextId = 1L;
        return true;
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
    public void deleteOutOfDate() {
        tasks.removeIf(task -> task.getEndDate().isBefore(LocalDateTime.now()));
    }

    @Override
    public boolean update(Task task) {
        if (task == null)
            return false;
        Task currentTask = getTaskById(task.getId());
        if (currentTask == null)
            return false;
        tasks.set(tasks.indexOf(currentTask), task);
        return true;
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
                .sorted(Comparator.comparing(Task::getId))
                .collect(toList());
    }

    @Override
    public List<Task> getAllOutstandingTasksByUserIdForToday(Long userId) {
        return tasks.stream()
                .filter(task -> task.getUserId().equals(userId) &&
                        task.getEndDate().isAfter(LocalDateTime.now()) &&
                        task.getDueDate().isBefore(LocalDateTime.now().plusDays(1).with(LocalTime.MIN)))
                .sorted(Comparator.comparing(Task::getId))
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

    public List<Task> searchTaskByDescription(String description) {
        List<Task> taskList = new ArrayList<>();
        String dscrptn;
        for (Task tsk : tasks) {
            dscrptn = tsk.getDescription();
            dscrptn = dscrptn.toLowerCase();
            if (dscrptn.contains(description.toLowerCase())) {
                //search string found in description
                taskList.add(tsk);
            }
        }
        return taskList;
    }

    @Override
    public List<Task> searchTaskByDate(LocalDateTime date) {
        List<Task> tasksList = new ArrayList<>();

        for (Task tsk : tasks) {
            if (tsk.getDueDate().isEqual(date) ||
                    tsk.getEndDate().isEqual(date)) {
                tasksList.add(tsk);
            }
        }
        return tasksList;
    }
    @Override
    public List<Task> searchTaskByDateRange(LocalDateTime start, LocalDateTime end, SearchDateType type) {
        List<Task> tasksList = new ArrayList<>();

        switch (type) {
            case DUE_DATE -> {
                for (Task tsk : tasks) {
                    LocalDateTime dueDate = tsk.getDueDate();
                    //check if due date is in provided range, inclusively
                    if (dueDate.isEqual(start)) {
                        tasksList.add(tsk);
                    }
                    if (dueDate.isEqual(end)) {
                        tasksList.add(tsk);
                    }
                    if (dueDate.isBefore(end) && dueDate.isAfter(start)) {
                        tasksList.add(tsk);
                    }
                }
                break;
            }
            case END_DATE -> {
                for (Task tsk : tasks) {
                    LocalDateTime endDate = tsk.getEndDate();
                    //check if end date is in provided range, inclusively
                    if (endDate.isEqual(start)) {
                        tasksList.add(tsk);
                    }
                    if (endDate.isEqual(end)) {
                        tasksList.add(tsk);
                    }
                    if (endDate.isBefore(end) && endDate.isAfter(start)) {
                        tasksList.add(tsk);
                    }
                }
                break;
            }
        }
        return tasksList;
    }

    @Override
    public List<Task> searchTasks(String searchPhrase, Long userID) {
        List<Task> tasksList = new ArrayList<>();
        String taskFullStrDescription;
        for (Task tsk : tasks) {
            if (Objects.equals(tsk.getUserId(), userID)) {
                taskFullStrDescription = tsk.getDescription() + tsk.getEndDate() + tsk.getDueDate();
                if (taskFullStrDescription.contains(searchPhrase)) {
                    tasksList.add(tsk);
                }
            }
        }
        return tasksList;
    }
}
