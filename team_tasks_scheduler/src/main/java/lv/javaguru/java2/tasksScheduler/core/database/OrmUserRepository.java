package lv.javaguru.java2.tasksScheduler.core.database;

import lv.javaguru.java2.tasksScheduler.core.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@Component
@Transactional
public class OrmUserRepository implements UsersRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public boolean save(User user) {
        Serializable result = sessionFactory.getCurrentSession().save(user);
        //if user was saved than id field should have real value
        return (Long)result != null;
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery (
                "delete User where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean update(User user) {
        sessionFactory.getCurrentSession().update(user);
        return true;
    }

    @Override
    public boolean existsByName(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM User b where username = :username");
        query.setParameter("username", username);
        List<User> result =  query.getResultList();

        return !result.isEmpty();
    }

    @Override
    public User getUserById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM User b where id = :id");
        query.setParameter("id", id);
        List<User> result =  query.getResultList();

        return result.stream().findAny().orElse(null);
    }

    @Override
    public User getUserByNameAndPassword(String username, String password) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select u FROM User u WHERE username = :username AND password = :user_password");
        query.setParameter("username", username);
        query.setParameter("user_password", password);
        List<User> result = query.getResultList();

        return result.stream().findAny().orElse(null);
    }

    @Override
    public List<User> getUsersAcceptedReminders() {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select u FROM User u WHERE true = send_reminder", User.class);
        return query.getResultList();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users =  sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
        return users;
    }

    @Override
    public List<User> getUsersByUsername(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select u FROM User u where username LIKE :username");
        query.setParameter("username", "%"+username+"%");
        return query.getResultList();
    }

    @Override
    public List<User> getUsersByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select u FROM User u where email LIKE :email");
        query.setParameter("email", "%"+email+"%");
        return query.getResultList();
    }

    @Override
    public List<User> getUsersByUsernameAndEmail(String username, String email) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select u FROM User u where email LIKE :email AND " +
                        "username LIKE :username");
        query.setParameter("email", "%"+email+"%");
        query.setParameter("username", "%"+username+"%");
        return query.getResultList();
    }
}
