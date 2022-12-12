package generalPackage.core.database;


import generalPackage.core.domain.Transactions;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ORMTransactionDatabase {

    @Autowired
    private SessionFactory sessionFactory;

    public void decreaseBalanceRecord(Transactions transactions) {
//        Query query = sessionFactory.getCurrentSession().createQuery(
//                "UPDATE transactions "
//        );
        sessionFactory.getCurrentSession().save(transactions);
    }

}
