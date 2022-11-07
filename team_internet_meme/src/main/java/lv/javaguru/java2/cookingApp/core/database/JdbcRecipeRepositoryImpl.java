package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.database.rowmappers.RecipeRowMapper;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JdbcRecipeRepositoryImpl implements RecipeRepository {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private RecipeRowMapper recipeRowMapper;

    @Override
    public Long save(Recipe recipe) {
        Long recipeID = saveDishName(recipe);
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
    public List<Recipe> searchByIngredients(List<String> ingredients) {
        String inSql = String.join(",", Collections.nCopies(ingredients.size(), "?"));
        String sql = "SELECT * FROM recipes " +
                "INNER JOIN recipes_to_ingredients ON recipes.id = recipes_to_ingredients.recipe_id " +
                "INNER JOIN ingredients ON ingredients.id = recipes_to_ingredients.ingredient_id " +
                "WHERE ingredient IN (%s) " +
                "GROUP BY dishName " +
                "HAVING COUNT(ingredient) = " + ingredients.size();
        return jdbcTemplate.query(String.format(sql, inSql), recipeRowMapper, ingredients.toArray());
    }

    private Long saveDishName(Recipe recipe) {
        SimpleJdbcInsert insertIntoRecipes = new SimpleJdbcInsert(jdbcTemplate).withTableName("recipes").usingGeneratedKeyColumns("id");
        Map<String, String> recipeArgs = new HashMap<>();
        recipeArgs.put("dishName", recipe.getDishName());
        return insertIntoRecipes.executeAndReturnKey(recipeArgs).longValue();
    }

//    private void saveIngredients(Recipe recipe, Long recipeID) {
//        SimpleJdbcInsert insertIntoIngredients = new SimpleJdbcInsert(jdbcTemplate).withTableName("ingredients").usingGeneratedKeyColumns("id");
//        for (Ingredient ingredient : recipe.getIngredients()) {
//            String sql1 = "SELECT * FROM ingredients WHERE ingredient LIKE ?";
//            List<Long> duplicateIngredientId = jdbcTemplate.query(sql1, (rs, rowNum) -> rs.getLong("id"), ingredient.getName());
//            Optional<Long> duplicateIngredientOpt = duplicateIngredientId.stream().findFirst();
//            if (duplicateIngredientOpt.isEmpty() ) {
//                Map<String, Object> ingredientArgs = new HashMap<>();
//                ingredientArgs.put("ingredient", ingredient.getName());
//                ingredientArgs.put("recipe_id", recipeID);
//                Long ingredientID = insertIntoIngredients.executeAndReturnKey(ingredientArgs).longValue();
//
//                String sql = "INSERT INTO recipes_to_ingredients VALUES (?, ?, ?, ?)";
//                Object[] args = new Object[]{recipeID, ingredientID, ingredient.getAmount(), ingredient.getMeasurement()};
//                jdbcTemplate.update(sql, args);
//            } else {
//                String sql = "INSERT INTO recipes_to_ingredients VALUES (?, ?, ?, ?)";
//                Object[] args = new Object[]{recipeID, duplicateIngredientOpt.get(), ingredient.getAmount(), ingredient.getMeasurement()};
//                jdbcTemplate.update(sql, args);
//            }
//        }
//    }
//
//    private void saveCookingSteps(Recipe recipe, Long recipeID) {
//        for (CookingStep cookingStep : recipe.getCookingSteps()) {
//            String sql = "INSERT INTO cooking_steps (recipe_id, step_order, instruction) VALUES (?, ?, ?)";
//            Object[] args = new Object[]{recipeID, cookingStep.getStepOrder(), cookingStep.getStepDescription()};
//            jdbcTemplate.update(sql, args);
//        }
//    }


}
