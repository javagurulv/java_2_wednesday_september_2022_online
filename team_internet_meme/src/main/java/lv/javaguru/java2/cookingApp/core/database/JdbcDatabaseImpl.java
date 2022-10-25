package lv.javaguru.java2.cookingApp.core.database;

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
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JdbcDatabaseImpl implements Database {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private RecipeRowMapper recipeRowMapper;

    @Override
    public Long save(Recipe recipe) {
        Long recipeID = saveDishName(recipe);
        saveIngredients(recipe, recipeID);
        saveCookingSteps(recipe, recipeID);
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
        List<Recipe> recipe = jdbcTemplate.query(sql, recipeRowMapper, id);
        return recipe.stream().findFirst();
    }

    @Override
    public List<Recipe> getAllRecipes() {
        String sql = "SELECT * FROM recipes";
        return jdbcTemplate.query(sql, recipeRowMapper);
    }

    @Override
    public List<Recipe> find(SearchCriteria searchCriteria) {
        List<Recipe> allRecipes = getAllRecipes();
        return allRecipes.stream().filter(searchCriteria).collect(Collectors.toList());
    }

    private Long saveDishName(Recipe recipe) {
        SimpleJdbcInsert insertIntoRecipes = new SimpleJdbcInsert(jdbcTemplate).withTableName("recipes").usingGeneratedKeyColumns("id");
        Map<String, String> recipeArgs = new HashMap<>();
        recipeArgs.put("dishName", recipe.getDishName());
        return insertIntoRecipes.executeAndReturnKey(recipeArgs).longValue();
    }

    private void saveIngredients(Recipe recipe, Long recipeID) {
        SimpleJdbcInsert insertIntoIngredients = new SimpleJdbcInsert(jdbcTemplate).withTableName("ingredients").usingGeneratedKeyColumns("id");
        for (Ingredient ingredient : recipe.getIngredients()) {
            String sql1 = "SELECT * FROM ingredients WHERE ingredient LIKE ?";
            Long duplicateIngredientId = jdbcTemplate.queryForObject(sql1, (rs, rowNum) -> rs.getLong("id"), ingredient.getName());

            if (duplicateIngredientId == null) {
                Map<String, Object> ingredientArgs = new HashMap<>();
                ingredientArgs.put("ingredient", ingredient.getName());
                ingredientArgs.put("recipe_id", recipeID);
                Long ingredientID = insertIntoIngredients.executeAndReturnKey(ingredientArgs).longValue();

                String sql = "INSERT INTO recipes_to_ingredients VALUES (?, ?, ?, ?)";
                Object[] args = new Object[]{recipeID, ingredientID, ingredient.getAmount(), ingredient.getMeasurement()};
                jdbcTemplate.update(sql, args);
            } else {
                String sql = "INSERT INTO recipes_to_ingredients VALUES (?, ?, ?, ?)";
                Object[] args = new Object[]{recipeID, duplicateIngredientId, ingredient.getAmount(), ingredient.getMeasurement()};
                jdbcTemplate.update(sql, args);
            }
        }
    }

    private void saveCookingSteps(Recipe recipe, Long recipeID) {
        for (CookingStep cookingStep : recipe.getCookingSteps()) {
            String sql = "INSERT INTO cooking_steps (recipe_id, step_order, instruction) VALUES (?, ?, ?)";
            Object[] args = new Object[]{recipeID, cookingStep.getStepOrder(), cookingStep.getStepDescription()};
            jdbcTemplate.update(sql, args);
        }
    }


}
