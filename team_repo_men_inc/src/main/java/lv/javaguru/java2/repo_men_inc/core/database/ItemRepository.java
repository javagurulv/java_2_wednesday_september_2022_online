package lv.javaguru.java2.repo_men_inc.core.database;

import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;
import lv.javaguru.java2.repo_men_inc.core.domain.Item;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Item item) {
        sessionFactory.getCurrentSession().save(item);
    }

    public Item findById(Long id) {
        return sessionFactory.getCurrentSession().get(Item.class, id);
    }
}
