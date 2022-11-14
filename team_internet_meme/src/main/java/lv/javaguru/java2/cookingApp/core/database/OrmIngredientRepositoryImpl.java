package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrmIngredientRepositoryImpl implements IngredientRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void saveIngredients(List<Ingredient> ingredients, Long recipeId) {
        Session session = sessionFactory.getCurrentSession();
        for (Ingredient ingredient : ingredients) {
            String hql = "SELECT i FROM Ingredient i WHERE ingredient LIKE :ingredient";
            List<Ingredient> duplicateIngredient = session.createQuery(hql, Ingredient.class).setParameter("ingredient", ingredient.getName()).getResultList();

            if (duplicateIngredient.isEmpty()) {
                NativeQuery insertIntoIngredients = session.createNativeQuery("INSERT INTO ingredients(ingredient) VALUES (?)");
                insertIntoIngredients.setParameter(1, ingredient.getName()).executeUpdate();

                BigInteger ingredientId = (BigInteger) session.createSQLQuery("SELECT LAST_INSERT_ID()")
                        .uniqueResult();


                NativeQuery insertIntoRecipesToIngredients = session.createNativeQuery("INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement) VALUES (?, ?, ?, ?)");
                insertIntoRecipesToIngredients.setParameter(1, recipeId);
                insertIntoRecipesToIngredients.setParameter(2, ingredientId);
                insertIntoRecipesToIngredients.setParameter(3, ingredient.getAmount());
                insertIntoRecipesToIngredients.setParameter(4, ingredient.getMeasurement());
                insertIntoRecipesToIngredients.executeUpdate();
            } else {

                NativeQuery insertIntoRecipesToIngredients = session.createNativeQuery("INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement) VALUES (?, ?, ?, ?)");
                insertIntoRecipesToIngredients.setParameter(1, recipeId);
                insertIntoRecipesToIngredients.setParameter(2, duplicateIngredient.get(0).getId());
                insertIntoRecipesToIngredients.setParameter(3, ingredient.getAmount());
                insertIntoRecipesToIngredients.setParameter(4, ingredient.getMeasurement());
                insertIntoRecipesToIngredients.executeUpdate();
            }
        }
    }


    @Override
    public List<Ingredient> getIngredientsByRecipeId(Long id) {
        return sessionFactory.getCurrentSession().createQuery("Select i FROM Ingredient i where recipe_id = " + id, Ingredient.class).getResultList();
    }
}
