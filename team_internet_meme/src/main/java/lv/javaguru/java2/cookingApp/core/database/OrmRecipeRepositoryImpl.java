package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
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
        return sessionFactory.getCurrentSession().createQuery("delete Recipe where id = :id")
                .setParameter("id", id).executeUpdate() == 1;
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Recipe.class, id));
    }

    @Override
    public boolean update(String name, Long id) {
        Optional<Recipe> recipe = getById(id);
        if (recipe.isEmpty()) {
            return false;
        }
        recipe.ifPresent(recipe1 -> recipe1.setDishName(name));
        return true;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return sessionFactory.getCurrentSession().createQuery("SELECT r FROM Recipe r", Recipe.class).getResultList();
    }

    @Override
    public List<Recipe> searchByIngredients(List<String> ingredients) {
        String inSql = String.join(",", Collections.nCopies(ingredients.size(), "?"));
        String sql = "SELECT * FROM recipes " +
                "INNER JOIN recipes_to_ingredients ON recipes.id = recipes_to_ingredients.recipe_id " +
                "INNER JOIN ingredients ON ingredients.id = recipes_to_ingredients.ingredient_id " +
                "WHERE ingredient IN (%s) " +
                "GROUP BY dishName " +
                "HAVING COUNT(ingredient) = " + ingredients.size();

        NativeQuery<Recipe> query = sessionFactory.getCurrentSession().createNativeQuery(String.format(sql, inSql), Recipe.class);
        for (int i = 0; i < ingredients.size(); i++) {
            query.setParameter(i + 1, ingredients.get(i));
        }
        return query.getResultList();
    }
}
