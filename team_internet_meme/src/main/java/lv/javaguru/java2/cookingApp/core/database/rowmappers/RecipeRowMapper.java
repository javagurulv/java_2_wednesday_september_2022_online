package lv.javaguru.java2.cookingApp.core.database.rowmappers;

import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RecipeRowMapper implements RowMapper<Recipe> {

    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Recipe recipe = new Recipe(rs.getString("dishName"));
        recipe.setId(rs.getLong("id"));
        return recipe;
    }

}