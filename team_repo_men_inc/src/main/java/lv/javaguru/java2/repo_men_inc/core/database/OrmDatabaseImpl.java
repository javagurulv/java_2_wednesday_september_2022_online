package lv.javaguru.java2.repo_men_inc.core.database;

import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Component
@Transactional
public class OrmDatabaseImpl implements Database {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public boolean save(Debtor debtor) {
        return sessionFactory.getCurrentSession().save(debtor) != null;
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Debtor where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public Debtor getById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select d FROM Debtor d where id = :id");
        query.setParameter("name", id);
        return (Debtor) query.getResultList().get(0);
    }

    @Override
    public Debtor getByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select d FROM debtors d where name = :name");
        query.setParameter("name", name);
        return (Debtor) query.getResultList().get(0);
    }

    @Override
    public Debtor getByNameAndListItem(String name, String listItem) {
        return null;
    }

    @Override
    public List<Debtor> getByListItem(String listItem) {
        return null;
    }

    @Override
    public List<Debtor> getAllDebtors() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT d FROM Debtor d", Debtor.class)
                .getResultList();
    }

    @Override
    public BigInteger saveItem(String itemName) {
        return null;
    }

    @Override
    public BigInteger saveDebtorAndReturnId(long index, String debtorName) {
        return null;
    }

    @Override
    public int updateList(BigInteger itemId, long debtorId) {
        return 0;
    }
}
