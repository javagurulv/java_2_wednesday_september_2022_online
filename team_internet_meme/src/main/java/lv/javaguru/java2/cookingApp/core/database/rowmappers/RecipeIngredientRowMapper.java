package lv.javaguru.java2.cookingApp.core.database.rowmappers;

import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.domain.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RecipeIngredientRowMapper implements RowMapper<RecipeIngredient> {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public RecipeIngredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        String sqlIngredient = "Select * FROM ingredients WHERE id = " + rs.getLong("ingredient_id");
        String sqlRecipe = "Select * FROM recipes WHERE id = " + rs.getLong("recipe_id");

        Ingredient ingredient = jdbcTemplate.query(sqlIngredient, new IngredientsRowMapper()).stream().findFirst().get();
        Recipe recipe = jdbcTemplate.query(sqlRecipe, new RecipeRowMapper()).stream().findFirst().get();

        RecipeIngredient recipeIngredient = new RecipeIngredient(recipe,
                ingredient,
                rs.getDouble("amount"),
                rs.getString("measurement") );
        recipeIngredient.setId(rs.getLong("id"));
        return recipeIngredient;
    }

}
