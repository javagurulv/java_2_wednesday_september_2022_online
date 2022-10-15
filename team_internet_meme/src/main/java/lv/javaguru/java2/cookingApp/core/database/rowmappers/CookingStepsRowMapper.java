package lv.javaguru.java2.cookingApp.core.database.rowmappers;

import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CookingStepsRowMapper implements RowMapper<CookingStep> {

	@Override
	public CookingStep mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new CookingStep(rs.getInt("step_order"), rs.getString("instruction"));
	}

}