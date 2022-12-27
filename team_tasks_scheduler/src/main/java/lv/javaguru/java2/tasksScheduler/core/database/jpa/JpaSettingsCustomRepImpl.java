package lv.javaguru.java2.tasksScheduler.core.database.jpa;


import lv.javaguru.java2.tasksScheduler.core.domain.Settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

@Component
public class JpaSettingsCustomRepImpl implements JpaSettingsCustomRep {
    @PersistenceContext
    private EntityManager entityManager;

    public boolean recordExists() {
        String hql = "SELECT s FROM Settings s";
        TypedQuery<Settings> query = entityManager.createQuery(hql, Settings.class);
        List<Settings> result =  query.getResultList();

        return !result.isEmpty();
    }

    public boolean passwordIsValid(String password) {
        String hql = "SELECT s FROM Settings s WHERE adminPassword = :adminPassword";
        TypedQuery<Settings> query = entityManager.createQuery(hql, Settings.class);
        query.setParameter("adminPassword", password);
        List<Settings> result =  query.getResultList();

        return !result.isEmpty();
    }

    public Settings getRecord() {
        String hql = "SELECT s FROM Settings s";
        TypedQuery<Settings> query = entityManager.createQuery(hql, Settings.class);
        query.setFirstResult(0).setMaxResults(1); //get only one row
        List<Settings> result =  query.getResultList();

        return result.stream().findAny().orElse(null);
    }
}
