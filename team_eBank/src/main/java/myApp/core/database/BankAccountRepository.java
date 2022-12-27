package myApp.core.database;

import myApp.core.domain.BankAccount;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
//@Transactional
public class BankAccountRepository {

    @Autowired
    SessionFactory sessionFactory;

    public void save(BankAccount bankAccount) {
        sessionFactory.getCurrentSession().save(bankAccount);
    }

    public BankAccount getById(Long id) {
        return sessionFactory.getCurrentSession()
                .get(BankAccount.class, id);
    }

    public boolean openAccount(String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "Update BankAccount set balance = : balance where personal_code =: personal_code");
        query.setParameter("balance", 0);
        query.setParameter("personal_code", personalCode);
        int result = query.executeUpdate();
        return result == 1;
    }
}
