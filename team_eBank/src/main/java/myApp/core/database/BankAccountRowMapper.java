package myApp.core.database;


import myApp.core.domain.BankAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountRowMapper implements RowMapper<BankAccount> {

    @Override
    public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(rs.getLong("id"));
        bankAccount.setName(rs.getString("name"));
        bankAccount.setSurname(rs.getString("surname"));
        bankAccount.setPersonalCode(rs.getString("personal_code"));
        if (rs.getString("role").equals("Roles.Admin")) {
            bankAccount.setRole("Roles.Admin");
        } else if (rs.getString("role").equals("Roles.Regular_User")) {
            bankAccount.setRole("Roles.Regular_user");
        }
        bankAccount.setBalance(rs.getInt("balance"));
        if (rs.wasNull()) {
            bankAccount.setBalance(null);
        }
        return bankAccount;
    }

}
