package generalPackage.core.database;

import generalPackage.core.domain.Accounts;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountsRowMapper implements RowMapper<Accounts> {

    @Override
    public Accounts mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Accounts accounts = new Accounts();
        accounts.setUserID(resultSet.getInt("id"));
        accounts.setName(resultSet.getString("name"));
        accounts.setBalance(resultSet.getInt("balance"));

        return accounts;
    }
}
