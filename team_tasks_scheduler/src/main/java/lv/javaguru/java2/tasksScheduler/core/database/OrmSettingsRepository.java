package lv.javaguru.java2.tasksScheduler.core.database;

import lv.javaguru.java2.tasksScheduler.core.domain.Settings;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

//@Component
//@Transactional
public class OrmSettingsRepository implements SettingsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean save(Settings settings) {
        Serializable result =  sessionFactory.getCurrentSession().save(settings);
        return (Long)result != null;
    }

    @Override
    public boolean recordExists() {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select s FROM Settings s");
        List<Settings> result =  query.getResultList();

        return !result.isEmpty();
    }

    @Override
    public boolean passwordIsValid(String password) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select s FROM Settings s");
        List<Settings> result =  query.getResultList();
        if (!result.isEmpty()) {
            return result.get(0).getAdminPassword().equals(password);
        }
        return false;
    }

    @Override
    public Settings getRecord() {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select s FROM Settings s");
        List<Settings> result =  query.getResultList();

        return result.stream().findAny().orElse(null);
    }
}
