package myApp.core.database;

import myApp.core.domain.BankAccount;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
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
}
