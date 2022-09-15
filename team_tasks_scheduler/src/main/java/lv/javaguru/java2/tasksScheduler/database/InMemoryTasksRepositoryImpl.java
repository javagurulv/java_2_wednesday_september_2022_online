package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.Task;
import lv.javaguru.java2.tasksScheduler.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryTasksRepositoryImpl implements TasksRepository {

    private Long nextId = 1L;
    private List<Task> tasks = new ArrayList<>();

    @Override
    public void save(Task task) {
        task.setId(nextId);
        nextId++;
        tasks.add(task);
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
    public void update(Task task) {
        //TODO
    }

    @Override
    public boolean exists(Task task) {
        //TODO
        return false;
    }

    @Override
    public List<Task> getAllTasksById(Long id) {
        //TODO
        return null;
    }

    @Override
    public List<Task> getAllTasksByUserId(Long userId) {
        //TODO
        return null;
    }

    @Override
    public List<Task> getAllTasksByUserIdAndDate(Long userId, Date date) {
        //TODO
        return null;
    }
}
