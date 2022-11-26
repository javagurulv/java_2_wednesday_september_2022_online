package lv.javaguru.java2.cookingApp.core.database;

import lv.javaguru.java2.cookingApp.core.database.rowmappers.CookingStepsRowMapper;
import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//@Component
public class JdbcCookingStepRepositoryImpl implements CookingStepRepository {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public void saveCookingSteps(List<CookingStep> cookingSteps, Long recipeID) {
        for (CookingStep cookingStep : cookingSteps) {
            String sql = "INSERT INTO cooking_steps (recipe_id, step_order, instruction) VALUES (?, ?, ?)";
            Object[] args = new Object[]{recipeID, cookingStep.getStepOrder(), cookingStep.getStepDescription()};
            jdbcTemplate.update(sql, args);
        }
    }

    @Override
    public List<CookingStep> getCookingStepsByRecipeId(Long recipeId) {
        String sql = "SELECT * FROM cooking_steps WHERE recipe_id = " + recipeId;
        return jdbcTemplate.query(sql, new CookingStepsRowMapper());
    }
}
