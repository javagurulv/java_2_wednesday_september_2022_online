package lv.javaguru.java2.tasksScheduler.core.database.jpa;


import lv.javaguru.java2.tasksScheduler.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaUsersCustomRepImpl implements JpaUsersCustomRep {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Modifying(clearAutomatically = true)
    public boolean update(User user) {
        String hql = "UPDATE User u set u.username =:username, u.password =:password, " +
                                        " u.email =:email, u.sendReminders =:sendReminders " +
                                            " where u.id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword());
        query.setParameter("email", user.getEmail());
        query.setParameter("sendReminders", user.getSendReminders());
        query.setParameter("id", user.getId());

        int result = query.executeUpdate(); //should update only one row

        return result == 1;
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

}
