package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.database.rowmappers.IngredientsRowMapper;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Component
public class JdbcIngredientRepositoryImpl implements IngredientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveRecipeIngredients(List<Ingredient> ingredients, Long recipeID) {
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

                String sql = "INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement) VALUES (?, ?, ?, ?)";
                Object[] args = new Object[]{recipeID, ingredientID, ingredient.getAmount(), ingredient.getMeasurement()};
                jdbcTemplate.update(sql, args);
            } else {
                String sql = "INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement) VALUES (?, ?, ?, ?)";
                Object[] args = new Object[]{recipeID, duplicateIngredientOpt.get(), ingredient.getAmount(), ingredient.getMeasurement()};
                jdbcTemplate.update(sql, args);
            }
        }
    }

    @Override
    public void save(Ingredient ingredient) {

    }


    @Override
    public List<Ingredient> getIngredientsByRecipeId(Long recipeId) {
        String sql1 = "SELECT ingredients.ingredient, recipes_to_ingredients.amount, measurement " +
                "FROM ingredients INNER JOIN recipes_to_ingredients ON ingredients.id = ingredient_id " +
                "WHERE recipe_id = " + recipeId;
        return jdbcTemplate.query(sql1, new IngredientsRowMapper());
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM ingredients WHERE id = " + id) == 1;
    }

    @Override
    public Optional<Ingredient> getById(Long id) {
        return Optional.empty();
    }
}
