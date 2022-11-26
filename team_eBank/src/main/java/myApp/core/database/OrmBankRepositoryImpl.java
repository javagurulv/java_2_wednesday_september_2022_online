package myApp.core.database;

import myApp.core.domain.BankAccount;
import myApp.core.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmBankRepositoryImpl implements BankRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addBankAccount(BankAccount bankAccount) {
     //   sessionFactory.getCurrentSession().save(user);
        //BankAccount bankAccount1 = new BankAccount(user, bankAccount.getName(), bankAccount.getSurname(), bankAccount.getPersonalCode());
        sessionFactory.getCurrentSession().save(bankAccount);
    }

    @Override
    public boolean deleteBankAccount(String personalCode) {
        Query queryBankAccount = sessionFactory.getCurrentSession().createQuery("Select b From BankAccount b" +
                " Where personal_code =: personal_code", BankAccount.class);
        queryBankAccount.setParameter("personal_code",personalCode);
        Optional<BankAccount> bankAccount = queryBankAccount.uniqueResultOptional();
        Query queryDeleteBankAccount = sessionFactory.getCurrentSession().createQuery(
                "delete BankAccount where personal_code =: personal_code");
        queryDeleteBankAccount.setParameter("personal_code", personalCode);
        int result = queryDeleteBankAccount.executeUpdate();
        Query queryUser = sessionFactory.getCurrentSession().createQuery(
                "delete User where id = :id");
        queryUser.setParameter("id", bankAccount.get().getId());
        int result2 = queryUser.executeUpdate();
        return result == 1 && result2 == 1;
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM BankAccount b", BankAccount.class)
                .getResultList();
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
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
    public List<BankAccount> findByNameAndSurnameAndPersonalCode(String name,
                                                                 String surname,
                                                                 String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM BankAccount b where name = : name AND surname = : surname AND personal_code = personalCode");
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        query.setParameter("personal_code", personalCode);
        return query.getResultList();
    }

    @Override
    public boolean openAccount(String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "Update BankAccount set balance = : balance where personal_code =: personal_code");
        query.setParameter("balance", 0);
        query.setParameter("personal_code", personalCode);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean closeAccount(String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "Update BankAccount set balance = : balance where personal_code =: personal_code");
        query.setParameter("balance", null);
        query.setParameter("personal_code", personalCode);
        int result = query.executeUpdate();
        return result == 1;
    }


    @Override
    public Optional<BankAccount> seeYourAccount(String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM BankAccount b where personal_code =: personal_code");
        query.setParameter("personal_code", personalCode);
        return query.uniqueResultOptional();
    }

    @Override
    public boolean bankTransfer(String personalCode, String anotherPersonalCode, int value) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE BankAccount SET balance = balance - balance where personal_code =: personal_code");
        query.setParameter("personal_code", personalCode);
        int resultOne = query.executeUpdate();

        Query queryTwo = sessionFactory.getCurrentSession().createQuery(
                "UPDATE BankAccount SET balance = balance + balance where personal_code =: personal_code");
        queryTwo.setParameter("personal_code", anotherPersonalCode);
        int resultTwo = queryTwo.executeUpdate();
        return resultOne == 1 && resultTwo == 1;
    }
}