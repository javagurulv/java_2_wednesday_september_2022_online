package generalPackage.core.database;

import generalPackage.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

// 19.11 temporary add:


@Component
public class JdbcAccountDatabaseImpl implements Database {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionDatabase transactionDatabase;

    @Override
    public void addAccount(Accounts accounts) {
        jdbcTemplate.update(
                "INSERT accounts (name) "
                        + "VALUES (?)",
                accounts.getName()
        );
    }

    @Override
    public boolean deleteAccount(int userID) {
        String sql = "DELETE FROM accounts WHERE id = ?";
        Object[] args = new Object[]{userID};

        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Accounts> getAllAccounts() {
        String sql = "SELECT * FROM accounts";
        return jdbcTemplate.query(sql, new AccountsRowMapper());
    }

    @Override
    public boolean increaseBalance(int userID, int amount) {
        String sql = "UPDATE accounts SET balance = balance + ? where id = ?";
        Object[] args = new Object[]{amount, userID};

        //     19.11 temporary add:
//        if ((jdbcTemplate.update(sql, args) == 1)) {
            transactionDatabase.increaseBalanceRecord(userID, amount);
//        }
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean decreaseBalance(int userID, int amount) {
        String sql = "UPDATE accounts SET balance = balance - ? where id = ?";
        Object[] args = new Object[]{amount, userID};

        String sqlCheck = "SELECT balance from accounts WHERE id = ?";
        Object[] argsCheck = new Object[]{userID};
        int startingBalance = jdbcTemplate.queryForObject(sqlCheck, argsCheck, Integer.class);
//        19.11 temporary add:
        if (startingBalance >= amount) {
            jdbcTemplate.update(sql, args);
            transactionDatabase.decreaseBalanceRecord(userID, amount);
        }
        else {
            System.out.println("Insufficient funds");
        }
        return startingBalance == jdbcTemplate.queryForObject(sqlCheck, argsCheck, Integer.class) - amount;
    }

    @Override
    public int printBalance(int userID) {
        String sql = "SELECT balance FROM accounts WHERE id = ?";
        Object[] args = new Object[]{userID};
        return jdbcTemplate.queryForObject(sql, args, Integer.class);
    }

    @Override
    public boolean isExist(String name) {
        return false;
    }

    @Override
    public Accounts findUserByID(int userID) {
        String sql = "SELECT * FROM accounts WHERE id =?";
        Accounts accounts = jdbcTemplate.queryForObject("SELECT * FROM accounts WHERE id = ?", new AccountsRowMapper(), new Object[]{userID});
        return accounts;
    }

    @Override
    public List<Accounts> searchAccountByName(String name) {
        String sql = "SELECT * FROM accounts WHERE name = ?";
        Object[] args = new Object[]{name};
        return jdbcTemplate.query(sql, args, new AccountsRowMapper());
    }
}
