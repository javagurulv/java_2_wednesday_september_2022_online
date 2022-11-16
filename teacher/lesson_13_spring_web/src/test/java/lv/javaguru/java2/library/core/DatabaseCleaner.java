package lv.javaguru.java2.library.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCleaner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void clean() {
		for(String tableName : getTableNames()) {
			String sql = "delete from " + tableName;
			jdbcTemplate.execute(sql);
		}
	}

	private List<String> getTableNames() {
		List<String> tableNames = new ArrayList<>();
		tableNames.add("reader_books");
		tableNames.add("readers");
		tableNames.add("books");
		return tableNames;
	}

}