package generalPackage.core.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class TransactionDatabase {


@Autowired
private JdbcTemplate jdbcTemplate;

    public void decreaseBalanceRecord (int userID, int amount) {
        String sql = "INSERT INTO transactions (id, amount, time)\n" +
                "VALUES (?, ?, NOW());";
        Object[] args = new Object[]{userID, amount*(-1)};
        jdbcTemplate.update(sql,args);
    }

    public void increaseBalanceRecord (int userID, int amount){
        String sql = "INSERT INTO transactions (id, amount, time)\n" +
                "VALUES (?, ?, NOW());";
        Object[] args = new Object[]{userID, amount};
        jdbcTemplate.update(sql,args);
    }

}
