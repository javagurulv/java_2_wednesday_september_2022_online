package lv.javaguru.java2.cookingApp.core.database.rowmappers;

import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class RecipeRowMapper implements RowMapper<Recipe> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
//        String sql1 = "SELECT ingredients.ingredient, recipes_to_ingredients.amount, measurement " +
//                "FROM ingredients INNER JOIN recipes_to_ingredients ON id = ingredient_id " +
//                "WHERE recipe_id = " + rs.getLong("recipes.id");
//        List<Ingredient> ingredients = jdbcTemplate.query(sql1, new IngredientsRowMapper());
//
//        String sql2 = "SELECT * FROM cooking_steps WHERE recipe_id = " + rs.getLong("recipes.id");
//        List<CookingStep> cookingSteps = jdbcTemplate.query(sql2, new CookingStepsRowMapper());

        Recipe recipe = new Recipe(rs.getString("dishName"));
        recipe.setId(rs.getLong("id"));
        return recipe;
    }

}