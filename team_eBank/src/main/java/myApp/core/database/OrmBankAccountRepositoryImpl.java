package myApp.core.database;

import myApp.core.domain.BankAccount;
import myApp.core.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrmBankAccountRepositoryImpl implements BankAccountRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addBankAccount(BankAccount bankAccount, User user) {
        sessionFactory.getCurrentSession().save(user);
        sessionFactory.getCurrentSession().save(bankAccount);
    }

    @Override
    public boolean deleteBankAccount(String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete BankAccount where personal_code = :personalCode");
        query.setParameter("personal_code", personalCode);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM BankAccount b", BankAccount.class)
                .getResultList();
    }
    @Override
    public List<User> getAllUsers() {
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
        List<User> secondUsers = users;
        return secondUsers;

    }
    @Override
    public List<BankAccount> findByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM BankAccount b where name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<BankAccount> findBySurname(String surname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM BankAccount b where surname = :surname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public List<BankAccount> findByPersonalCode(String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM BankAccount b where personal_code = :personalCode");
        query.setParameter("personal_code", personalCode);
        return query.getResultList();
    }

    @Override
    public List<BankAccount> findByNameAndSurname(String name, String surname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM BankAccount b where name = : name AND surname = : surname");
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public List<BankAccount> findByNameAndPersonalCode(String name, String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM BankAccount b where name = : name AND personal_code = : personalCode");
        query.setParameter("name", name);
        query.setParameter("personal_code", personalCode);
        return query.getResultList();
    }

    @Override
    public List<BankAccount> findBySurnameAndPersonalCode(String surname, String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM BankAccount b where surname = : surname AND personal_code = : personalCode");
        query.setParameter("surname", surname);
        query.setParameter("personal_code", personalCode);
        return query.getResultList();
    }

    @Override
    public     List<BankAccount> findByNameAndSurnameAndPersonalCode(String name,
                                                                     String surname,
                                                                     String personalCode ){
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM BankAccount b where name = : name AND surname = : surname AND personal_code = personalCode");
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        query.setParameter("personal_code", personalCode);
        return query.getResultList();
    }
}