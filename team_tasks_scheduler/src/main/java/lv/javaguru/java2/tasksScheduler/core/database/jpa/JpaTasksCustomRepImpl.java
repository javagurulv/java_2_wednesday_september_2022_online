package lv.javaguru.java2.tasksScheduler.core.database.jpa;


import lv.javaguru.java2.tasksScheduler.core.domain.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;

import static lv.javaguru.java2.tasksScheduler.utils.ValueChecking.checkAdjustMySqlDateRange;

public class JpaTasksCustomRepImpl implements JpaTasksCustomRep {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean update(Task task) {
        return true;
    }

    @Override
    public int deleteByUserIdTillDate(Long userId, LocalDateTime endDate) {
        return 0;
    }

    @Override
    public boolean exists(Task task) {
        return false;
    }

    @Override
    public List<Task> getAllOutstandingTasksByUserIdTillDate(Long userId, LocalDateTime endDate) {
        LocalDateTime pEndDate = checkAdjustMySqlDateRange(endDate);

        String hql = "select t FROM Task t where userId = :userId AND " +
                        "end_date > NOW() AND " +
                        "due_date < :endDate";
        TypedQuery<Task> query = entityManager.createQuery(hql, Task.class);
        query.setParameter("userId", userId);
        query.setParameter("endDate", pEndDate);

        return query.getResultList();
    }

    @Override
    public List<Task> getAllTasksReadyForDueDateUpdate(Long userId) {
        return null;
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
