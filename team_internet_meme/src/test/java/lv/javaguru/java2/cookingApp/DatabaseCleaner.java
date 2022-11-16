
package lv.javaguru.java2.cookingApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseCleaner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void clean() {
        for (String tableName : getTableNames()) {
            String sql = "DELETE FROM " + tableName;
            jdbcTemplate.execute(sql);
        }
    }

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        tableNames.add("cooking_steps");
        tableNames.add("recipes_to_ingredients");
        tableNames.add("ingredients");
        tableNames.add("recipes");
        return tableNames;
    }

}
