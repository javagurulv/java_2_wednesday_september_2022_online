package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class OrmRecipeRepositoryImpl implements RecipeRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public Long save(Recipe recipe) {
        return (Long) sessionFactory.getCurrentSession().save(recipe);
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete Recipe where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        return Optional.of(sessionFactory.getCurrentSession().get(Recipe.class, id));
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return sessionFactory.getCurrentSession().createQuery("SELECT r FROM Recipe r", Recipe.class).getResultList();
    }

    @Override
    public List searchByIngredients(List<String> ingredients) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Recipe.class);
        for (String ingredient : ingredients) {
            criteria.add(Restrictions.like("ingredient", ingredient));
        }
        return criteria.list();
    }
}
