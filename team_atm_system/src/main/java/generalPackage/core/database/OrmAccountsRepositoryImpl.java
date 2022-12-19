package generalPackage.core.database;

import generalPackage.core.domain.Accounts;
import generalPackage.core.domain.Transactions;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrmAccountsRepositoryImpl implements AccountsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    //    @Autowired
//    Transactions transactions;
    @Autowired
    ORMTransactionRepository transactionRepository;

    @Override
    public void addAccount(Accounts accounts) {
        sessionFactory.getCurrentSession().save(accounts);
    }

    @Override
    public boolean deleteAccount(int userID) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Accounts WHERE id = :id");
        query.setParameter("id", userID);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Accounts> getAllAccounts() {
        return sessionFactory.getCurrentSession().createQuery(
                        "select a FROM Accounts a", Accounts.class)
                .getResultList();
    }

    @Override
    public boolean increaseBalance(int userID, int amount) {
        transactionRepository.inreaseBalanceRecord(new Transactions(userID,amount));
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE Accounts SET balance = :b1 where id =:id");
        query.setParameter("b1", findUserByID(userID).getBalance() + amount);
        query.setParameter("id", userID);
        return query.executeUpdate() == 1;
    }

    @Override
    public boolean decreaseBalance(int userID, int amount) {
        transactionRepository.decreaseBalanceRecord(new Transactions(userID,amount*(-1)));
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE Accounts SET balance = :b1 where id =:id");
        query.setParameter("b1", findUserByID(userID).getBalance() - amount);
        query.setParameter("id", userID);
        return query.executeUpdate() == 1;
    }

    @Override
    public int printBalance(int userID) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT balance from Accounts where id =:id");
//        query.setParameter("balance", findUserByID(userID).getBalance());
        query.setParameter("id", userID);
        return (int) query.getSingleResult();
    }

    @Override
    public boolean isExist(String name) {
        return false;
    }

    @Override
    public Accounts findUserByID(int userID) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select a FROM Accounts a where id = :id");
        query.setParameter("id", userID);
        return (Accounts) query.uniqueResult();
    }

    @Override
    public List<Accounts> searchAccountByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select a FROM Accounts a where name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }


}
