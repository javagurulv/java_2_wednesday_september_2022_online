package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.database.rowmappers.IngredientsRowMapper;
import lv.javaguru.java2.cookingApp.core.database.rowmappers.RecipeRowMapper;
import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.services.searchcriteria.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbcDatabaseImpl implements Database {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Recipe recipe) {
        SimpleJdbcInsert insertIntoRecipes = new SimpleJdbcInsert(jdbcTemplate).withTableName("recipes").usingGeneratedKeyColumns("id");
        SimpleJdbcInsert insertIntoIngredients = new SimpleJdbcInsert(jdbcTemplate).withTableName("ingredients").usingGeneratedKeyColumns("id");

        Map<String, String> recipeArgs = new HashMap<>();
        recipeArgs.put("dishName", recipe.getDishName());
        Long recipeID = insertIntoRecipes.executeAndReturnKey(recipeArgs).longValue();

        for (Ingredient ingredient : recipe.getIngredients()) {
            String sql1 = "SELECT * FROM ingredients WHERE ingredient LIKE ?";
            Object[] args1 = new Object[]{ingredient.getName()};
            List<Ingredient> duplicateIngredient = jdbcTemplate.query(sql1, new IngredientsRowMapper());
            if (duplicateIngredient.isEmpty()) {
                Map<String, Object> ingredientArgs = new HashMap<>();
                ingredientArgs.put("ingredient", ingredient.getName());
                ingredientArgs.put("recipe_id", recipeID);
                Long ingredientID = insertIntoIngredients.executeAndReturnKey(ingredientArgs).longValue();

                String sql = "INSERT INTO recipes_to_ingredients VALUES (?, ?, ?, ?)";
                Object[] args = new Object[]{recipeID, ingredientID, ingredient.getAmount(), ingredient.getMeasurement()};
                jdbcTemplate.update(sql, args);
            }
        }

        for (CookingStep cookingStep : recipe.getCookingSteps()) {
            String sql = "INSERT INTO cooking_steps (recipe_id, step_order, instruction) VALUES (?, ?, ?)";
            Object[] args = new Object[]{recipeID, cookingStep.getStepOrder(), cookingStep.getStepDescription()};
            jdbcTemplate.update(sql, args);
        }
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
    public Recipe getById(Long id) {
        return null;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        String sql = "SELECT * FROM recipes";
        return jdbcTemplate.query(sql, new RecipeRowMapper());
    }

    @Override
    public List<Recipe> find(SearchCriteria searchCriteria) {
        return null;
    }
}
