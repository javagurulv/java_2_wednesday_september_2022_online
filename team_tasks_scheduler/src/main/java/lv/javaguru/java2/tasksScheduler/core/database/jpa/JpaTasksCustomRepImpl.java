package lv.javaguru.java2.tasksScheduler.core.database.jpa;


import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static lv.javaguru.java2.tasksScheduler.utils.ValueChecking.checkAdjustMySqlDateRange;

public class JpaTasksCustomRepImpl implements JpaTasksCustomRep {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Modifying(clearAutomatically = true)
    public boolean update(Task task) {
        String hql = "UPDATE Task t set t.description = :description, t.regularity = :regularity, " +
                " t.dueDate = :dueDate, t.endDate = :endDate " +
                " WHERE t.id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", task.getId());
        query.setParameter("description", task.getDescription());
        query.setParameter("regularity", task.getRegularity());
        query.setParameter("dueDate", task.getDueDate());
        query.setParameter("endDate", task.getEndDate());

        int result = query.executeUpdate(); //should update only one row

        return result == 1;
    }

    @Override
    @Modifying(clearAutomatically = true)
    public int deleteByUserIdTillDate(Long userId, LocalDateTime endDate) {
        Query query;
        LocalDateTime pEndDate = checkAdjustMySqlDateRange(endDate); //java range > mysql range

        if (userId == null) {
            query = entityManager.createQuery(
                    "DELETE FROM Task t WHERE t.endDate < :endDate");
        } else {
            query = entityManager.createQuery(
                    "DELETE FROM Task t WHERE t.userId=:userId AND t.endDate < :endDate");
            query.setParameter("userId", userId);
        }

        query.setParameter("endDate", pEndDate);

        return query.executeUpdate();
    }

    @Override
    public boolean exists(Task task) {
        String hql = "select t FROM Task t where " +
                "task_description = :description AND " +
                "regularity = :regularity AND " +
                "due_date = :dueDate AND " +
                "end_date = :endDate AND " +
                "user_id = :userId";
        TypedQuery<Task> query = entityManager.createQuery(hql, Task.class);

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
    public List<Task> getAllOutstandingTasksByUserIdTillDate(Long userId, LocalDateTime endDate) {
        LocalDateTime pEndDate = checkAdjustMySqlDateRange(endDate);

        String hql = "select t FROM Task t where userId = :userId AND " +
                        "end_date > NOW() AND " +
                        "due_date > NOW() AND " +
                        "due_date < :endDate";
        TypedQuery<Task> query = entityManager.createQuery(hql, Task.class);
        query.setParameter("userId", userId);
        query.setParameter("endDate", pEndDate);

        return query.getResultList();
    }

    @Override
    public List<Task> getAllTasksReadyForDueDateUpdate(Long userId) {
        String hql;
        TypedQuery<Task> query;
        List<Task> tasks;

        if (userId != null) {
            //MySQL TIMESTAMP(CURDATE()) is returning [DATE+00:00:00]
            hql = "SELECT t FROM Task t WHERE user_id = :userId AND (regularity > 0 AND " +
                    " end_date > CURRENT_DATE() AND due_date < CURRENT_DATE()) " +
                    " ORDER BY regularity";
            query = entityManager.createQuery(hql, Task.class);
            query.setParameter("userId", userId);
        } else {
            hql = "SELECT t FROM Task t WHERE regularity > 0 AND end_date > CURRENT_DATE() " +
                    " AND due_date < CURRENT_DATE() " +
                    " ORDER BY regularity ";
            query = entityManager.createQuery(hql, Task.class);//TODO recheck sql/hkl queries
        }
        return query.getResultList();
    }

    @Override
    public List<Task> searchTasks(String searchPhrase, Long userId) {
        String hql = "select t FROM Task t WHERE user_id = :userId AND " +
                "(task_description LIKE :searchPhrase OR " +
                "CONVERT(due_date, CHAR) LIKE :searchPhrase OR " +
                "CONVERT(end_date, CHAR) LIKE :searchPhrase)";
        TypedQuery<Task> query = entityManager.createQuery(hql, Task.class);
        query.setParameter("searchPhrase", "%"+searchPhrase+"%"); //adding wildcard
        query.setParameter("userId", userId);

        return query.getResultList();
    }
}
