package generalPackage.core.database;


import generalPackage.core.domain.Transactions;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ORMTransactionRepository {

    @Autowired
    private SessionFactory sessionFactory;

//    public void decreaseBalanceRecord(int userID, int amount) {
//        Query query = sessionFactory.getCurrentSession().createQuery(
//                "INSERT INTO transactions (user_id, amount) ");
//        query.setParameter("user_id", userID);
//        query.setParameter("amount", amount );
//        query.executeUpdate();
//    }

    public void decreaseBalanceRecord(Transactions transactions) {
        sessionFactory.getCurrentSession().save(transactions);
    }

    public void inreaseBalanceRecord(Transactions transactions) {
        sessionFactory.getCurrentSession().save(transactions);
    }


}
