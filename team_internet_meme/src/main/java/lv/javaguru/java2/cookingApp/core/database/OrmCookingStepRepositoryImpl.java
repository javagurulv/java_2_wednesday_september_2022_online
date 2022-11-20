package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrmCookingStepRepositoryImpl implements CookingStepRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void saveCookingSteps(List<CookingStep> cookingSteps, Long recipeId) {
        Session session = sessionFactory.getCurrentSession();
        for (CookingStep cookingStep : cookingSteps) {
            NativeQuery insertIntoCookingSteps = session.createNativeQuery("INSERT INTO cooking_steps (recipe_id, step_order, instruction) VALUES (?, ?, ?)");
            insertIntoCookingSteps.setParameter(1, recipeId);
            insertIntoCookingSteps.setParameter(2, cookingStep.getStepOrder());
            insertIntoCookingSteps.setParameter(3, cookingStep.getStepDescription());
            insertIntoCookingSteps.executeUpdate();
        }
    }

    @Override
    public List<CookingStep> getCookingStepsByRecipeId(Long id) {
        return sessionFactory.getCurrentSession().createQuery("FROM CookingStep WHERE recipe_id = " + id, CookingStep.class).getResultList();
    }
}
