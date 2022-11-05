package myApp.core.database;

import myApp.core.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Optional<User> logIn(String  login,String password) {
         Query query = sessionFactory.getCurrentSession().createQuery("Select u From User u " +
                 " Where login =: login And password =: password",User.class);
         query.setParameter("login", login);
         query.setParameter("password", password);
         return query.uniqueResultOptional();
    }

    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }
}