package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.enums.SearchDateType;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static lv.javaguru.java2.tasksScheduler.utils.ValueChecking.checkAdjustMySqlDateRange;

@Component
@Transactional
public class OrmTaskRepository implements TasksRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean save(Task task) {
        Serializable result = sessionFactory.getCurrentSession().save(task);
        //if user was saved than id field should have real value
        return (Long)result != null;
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete t Task t where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public int deleteByUserIdTillDate(Long userId, LocalDateTime endDate) {
        Query<Task> query;
        java.sql.Timestamp sqlEndDate = java.sql.Timestamp.valueOf(endDate);

        if (userId == null) {
            query = sessionFactory.getCurrentSession().createQuery(
                    "select t FROM Task t where end_date < :endDate", Task.class);
        }
        else {
            query = sessionFactory.getCurrentSession().createQuery(
                    "select t FROM Task t where userId = :userId AND " +
                                    "end_date < :endDate", Task.class);
            query.setParameter("userId", userId);
        }
        query.setParameter("endDate", sqlEndDate);
        return query.getResultList().size();
    }

    @Override
    public boolean update(Task task) {
        sessionFactory.getCurrentSession().update(task);
        return true;
    }

    @Override
    public boolean exists(Task task) {
        Query<Task> query = sessionFactory.getCurrentSession().createQuery(
                "select t FROM Task t where " +
                        "task_description = :description AND " +
                        "regularity = :regularity AND " +
                        "due_date = :dueDate AND " +
                        "end_date = :endDate AND " +
                        "user_id = :userId", Task.class);
        //check all fields
        query.setParameter("description", task.getDescription());
        query.setParameter("regularity", task.getRegularity());
        query.setParameter("dueDate", task.getDueDate());
        query.setParameter("endDate", task.getEndDate());
        query.setParameter("userId", task.getUserId());

        List<Task> result = query.getResultList();
        //should get only one or do not get at all
        return !result.isEmpty();
    }

    @Override
    public Task getTaskById(Long id) {
        Task task = (Task) sessionFactory.getCurrentSession().get(Task.class, id);
        return task;
    }

    @Override
    public List<Task> getAllOutstandingTasksByUserIdTillDate(Long userId, LocalDateTime endDate) {
        //MySQL date range from '1000-01-01 00:00:00' to '9999-12-31 23:59:59'.
        LocalDateTime pEndDate = checkAdjustMySqlDateRange(endDate);
        java.sql.Timestamp sqlEndDate = java.sql.Timestamp.valueOf(pEndDate);

        Query<Task> query = sessionFactory.getCurrentSession().createQuery(
                "select t FROM Task t where userId = :userId AND " +
                        "end_date > NOW() AND " +
                        "due_date < :endDate", Task.class);
        query.setParameter("userId", userId);
        query.setParameter("endDate", sqlEndDate);
        return query.getResultList();
    }

    @Override
    public List<Task> getAllTasksReadyForDueDateUpdate(Long userId) {
        return null;
    }

    @Override
    public List<Task> searchTaskByDescription(String description, Long userId) {
        Query<Task> query = sessionFactory.getCurrentSession().createQuery(
                "select t FROM Task t WHERE description like description AND " +
                        "user_id = : userId", Task.class);

        query.setParameter("description", "%"+description+"%");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Task> searchTaskByDate(LocalDateTime date, Long userId) {
        return null;
    }

    @Override
    public List<Task> searchTaskByDateRange(LocalDateTime start, LocalDateTime end, SearchDateType type) {
        return null;
    }

    @Override
    public List<Task> searchTasks(String searchPhrase, Long userID) {
        return null;
    }
}