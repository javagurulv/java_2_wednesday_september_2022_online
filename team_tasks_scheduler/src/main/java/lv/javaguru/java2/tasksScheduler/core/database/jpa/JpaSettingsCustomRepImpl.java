package lv.javaguru.java2.tasksScheduler.core.database.jpa;


import lv.javaguru.java2.tasksScheduler.core.domain.Settings;

import lv.javaguru.java2.tasksScheduler.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Override
    @Modifying(clearAutomatically = true)
    public boolean update(Settings settings) {
        String hql = "UPDATE Settings s set s.adminPassword =:adminPassword, s.emailFrom =:emailFrom, " +
                " s.emailPassword =:emailPassword, s.emailHost =:emailHost, " +
                " s.emailPort =:emailPort, s.emailProtocol =:emailProtocol " +
                " where s.id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("adminPassword", settings.getAdminPassword());
        query.setParameter("emailFrom", settings.getEmailFrom());
        query.setParameter("emailPassword", settings.getEmailPassword());
        query.setParameter("emailHost", settings.getEmailHost());
        query.setParameter("emailPort", settings.getEmailPort());
        query.setParameter("emailProtocol", settings.getEmailProtocol());
        query.setParameter("id", settings.getId());

        int result = query.executeUpdate(); //should update one row only

        return result == 1;
    }
}
