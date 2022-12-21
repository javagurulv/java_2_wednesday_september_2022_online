package lv.javaguru.java2.tasksScheduler.core.database.jpa;


import lv.javaguru.java2.tasksScheduler.core.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaUsersCustomRepImpl implements JpaUsersCustomRep {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public List<User> getUsersByUsername(String username) {
        String hql = "select u FROM User u where username LIKE :username";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        query.setParameter("username", "%"+username+"%");

        return query.getResultList();
    }

    @Override
    public List<User> getUsersByEmail(String email) {
        String hql = "select u FROM User u where email LIKE :email";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        query.setParameter("email", "%"+email+"%");

        return query.getResultList();
    }

    @Override
    public List<User> getUsersByUsernameAndEmail(String username, String email) {
        String hql = "select u FROM User u where email LIKE :email AND " +
                                                        "username LIKE :username";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        query.setParameter("email", "%"+email+"%");
        query.setParameter("username", "%"+username+"%");

        return query.getResultList();
    }

    public boolean existsByName(String username) {
        return false;
    }

   public List<User> getUsersAcceptedReminders() {
        return null;
    }
}
