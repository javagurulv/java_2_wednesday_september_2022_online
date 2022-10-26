package lv.javaguru.java2.cookingApp.core.database.rowmappers;

import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientsRowMapper implements RowMapper<Ingredient> {

	@Override
	public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(rs.getString("ingredient"), rs.getString("measurement"), rs.getDouble("amount"));
	}

}