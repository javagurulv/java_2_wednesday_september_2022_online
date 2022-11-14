package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.database.rowmappers.IngredientsRowMapper;
import lv.javaguru.java2.cookingApp.core.database.rowmappers.RecipeIngredientRowMapper;
import lv.javaguru.java2.cookingApp.core.domain.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JdbcRecipeIngredientRepositoryImpl implements RecipeIngredientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long save(RecipeIngredient recipeIngredient) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("recipes_to_ingredients").usingGeneratedKeyColumns("id");

        Map<String, Object> args = new HashMap<>();
        args.put("recipe_id", recipeIngredient.getRecipe().getId());
        args.put("ingredient_id", recipeIngredient.getIngredient().getId());
        args.put("amount", recipeIngredient.getAmount());
        args.put("measurement", recipeIngredient.getMeasurement());

        return simpleJdbcInsert.executeAndReturnKey(args).longValue();
    }

    @Override
    public Optional<RecipeIngredient> getById(Long id) {
        String sql1 = "SELECT * " +
                "FROM recipes_to_ingredients " +
                "WHERE recipe_id = " + id;
        return jdbcTemplate.query(sql1, new RecipeIngredientRowMapper()).stream().findFirst();
    }
}
