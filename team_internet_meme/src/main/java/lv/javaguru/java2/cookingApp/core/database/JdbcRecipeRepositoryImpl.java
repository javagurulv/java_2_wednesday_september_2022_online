package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.database.rowmappers.RecipeRowMapper;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.*;

//@Component
public class JdbcRecipeRepositoryImpl implements RecipeRepository {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Recipe recipe) {
        SimpleJdbcInsert insertIntoRecipes = new SimpleJdbcInsert(jdbcTemplate).withTableName("recipes").usingGeneratedKeyColumns("id");
        Map<String, String> recipeArgs = new HashMap<>();
        recipeArgs.put("dishName", recipe.getDishName());
        Long recipeID = insertIntoRecipes.executeAndReturnKey(recipeArgs).longValue();
        recipe.setId(recipeID);
        return recipeID;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM recipes " +
                "WHERE id = ?" ;
        Object[] args = new Object[]{id};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        String sql = "SELECT * FROM recipes WHERE id = ?";
        List<Recipe> recipe = jdbcTemplate.query(sql, new RecipeRowMapper(), id);
        return recipe.stream().findFirst();
    }

    @Override
    public boolean update(String name, Long id) {
        return false;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        String sql = "SELECT * FROM recipes";
        return jdbcTemplate.query(sql, new RecipeRowMapper());
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
        return jdbcTemplate.query(String.format(sql, inSql), new RecipeRowMapper(), ingredients.toArray());
    }

}
