package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.database.rowmappers.IngredientsRowMapper;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JdbcIngredientRepositoryImpl implements IngredientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveIngredients(List<Ingredient> ingredients, Long recipeID) {
        SimpleJdbcInsert insertIntoIngredients = new SimpleJdbcInsert(jdbcTemplate).withTableName("ingredients").usingGeneratedKeyColumns("id");
        for (Ingredient ingredient : ingredients) {
            String sql1 = "SELECT * FROM ingredients WHERE ingredient LIKE ?";
            List<Long> duplicateIngredientId = jdbcTemplate.query(sql1, (rs, rowNum) -> rs.getLong("id"), ingredient.getName());
            Optional<Long> duplicateIngredientOpt = duplicateIngredientId.stream().findFirst();
            if (duplicateIngredientOpt.isEmpty()) {
                Map<String, Object> ingredientArgs = new HashMap<>();
                ingredientArgs.put("ingredient", ingredient.getName());
                ingredientArgs.put("recipe_id", recipeID);
                Long ingredientID = insertIntoIngredients.executeAndReturnKey(ingredientArgs).longValue();

                String sql = "INSERT INTO recipes_to_ingredients VALUES (?, ?, ?, ?)";
                Object[] args = new Object[]{recipeID, ingredientID, ingredient.getAmount(), ingredient.getMeasurement()};
                jdbcTemplate.update(sql, args);
            } else {
                String sql = "INSERT INTO recipes_to_ingredients VALUES (?, ?, ?, ?)";
                Object[] args = new Object[]{recipeID, duplicateIngredientOpt.get(), ingredient.getAmount(), ingredient.getMeasurement()};
                jdbcTemplate.update(sql, args);
            }
        }
    }


    @Override
    public List<Ingredient> getIngredientsByRecipeId(Long recipeId) {
        String sql1 = "SELECT ingredients.ingredient, recipes_to_ingredients.amount, measurement " +
                "FROM ingredients INNER JOIN recipes_to_ingredients ON id = ingredient_id " +
                "WHERE recipe_id = " + recipeId;
        return jdbcTemplate.query(sql1, new IngredientsRowMapper());
    }
}
