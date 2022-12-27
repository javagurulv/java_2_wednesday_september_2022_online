package lv.javaguru.java2.repo_men_inc.core.database;

import lv.javaguru.java2.repo_men_inc.core.domain.ListItem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ListItemRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(ListItem listItem) {
        sessionFactory.getCurrentSession().save(listItem);
    }

    public ListItem findById(Long id) {
        return sessionFactory.getCurrentSession().get(ListItem.class, id);
    }

    public ListItem findByName(String name) {
        return sessionFactory.getCurrentSession().get(ListItem.class, name);
    }
}
