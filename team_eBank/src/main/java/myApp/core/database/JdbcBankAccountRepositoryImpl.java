package myApp.core.database;


import myApp.core.domain.BankAccount;
import myApp.core.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

//@Component
public class JdbcBankAccountRepositoryImpl {

    // @Autowired
    private JdbcTemplate jdbcTemplate;

    //  @Override
    /*
    public void addBankAccount(BankAccount bankAccount, User user) {
        jdbcTemplate.update(
                "INSERT INTO users (personal_code, password) "
                        + "VALUES (?,?)",
                user.getPersonalCode(), user.getPassword()
        );

        String role = "Roles.Admin";
        if (bankAccount.getRole().equals(Roles.Regular_user)) {
            role = "Roles.Regular_User";
        }
        jdbcTemplate.update(
                "INSERT INTO bank_accounts (name, surname, personal_code, role) "
                        + "VALUES (?, ?, ?, ?)",
                bankAccount.getName(), bankAccount.getSurname(), bankAccount.getPersonalCode(), role
        );
    }

     */

    //  @Override
    public boolean deleteBankAccount(String personalCode) {
        String sql = "DELETE FROM bank_accounts WHERE personal_code = ?";
        Object[] args = new Object[]{personalCode};
        boolean result = jdbcTemplate.update(sql, args) == 1;
        String sql2 = "DELETE FROM users WHERE personal_code = ?";
        Object[] args2 = new Object[]{personalCode};
        boolean result2 = jdbcTemplate.update(sql2, args2) == 1;
        return result && result2;
    }

    // @Override
    public List<BankAccount> getAllBankAccounts() {
        String sql = "SELECT * FROM bank_accounts";
        return jdbcTemplate.query(sql, new BankAccountRowMapper());
    }

    //  @Override
    public List<BankAccount> findByName(String name) {
        String sql = "SELECT * FROM bank_accounts WHERE name = ?";
        Object[] args = new Object[]{name};
        return jdbcTemplate.query(sql, args, new BankAccountRowMapper());
    }

    //  @Override
    public List<BankAccount> findBySurname(String surname) {
        String sql = "SELECT * FROM bank_accounts WHERE surname = ?";
        Object[] args = new Object[]{surname};
        return jdbcTemplate.query(sql, args, new BankAccountRowMapper());
    }

    //  @Override
    public List<BankAccount> findByPersonalCode(String personalCode) {
        String sql = "SELECT * FROM bank_accounts WHERE personal_code = ?";
        Object[] args = new Object[]{personalCode};
        return jdbcTemplate.query(sql, args, new BankAccountRowMapper());
    }

    //  @Override
    public List<BankAccount> findByNameAndSurname(String name, String surname) {
        String sql = "SELECT * FROM bank_accounts WHERE name = ? AND surname = ? ";
        Object[] args = new Object[]{name, surname};
        return jdbcTemplate.query(sql, args, new BankAccountRowMapper());
    }

    //  @Override
    public List<BankAccount> findByNameAndPersonalCode(String name, String personalCode) {
        String sql = "SELECT * FROM bank_accounts WHERE name = ? AND personalCode = ? ";
        Object[] args = new Object[]{name, personalCode};
        return jdbcTemplate.query(sql, args, new BankAccountRowMapper());
    }

    // @Override
    public List<BankAccount> findBySurnameAndPersonalCode(String surname, String personalCode) {
        String sql = "SELECT * FROM bank_accounts WHERE surname = ? AND personalCode = ? ";
        Object[] args = new Object[]{surname, personalCode};
        return jdbcTemplate.query(sql, args, new BankAccountRowMapper());
    }

    //  @Override
    public List<BankAccount> findByNameAndSurnameAndPersonalCode(String name, String surname, String personalCode) {
        String sql = "SELECT * FROM bank_accounts WHERE name =? AND surname = ? AND personalCode = ? ";
        Object[] args = new Object[]{name, surname, personalCode};
        return jdbcTemplate.query(sql, args, new BankAccountRowMapper());
    }

    //  @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    // @Override
    public boolean bankTransfer(String personalCode, String anotherPersonalCode, int value) {
        String sql = "UPDATE bank_accounts SET balance = balance - ? WHERE personal_code = ?";
        Object[] args = new Object[]{value, personalCode};
        boolean result = jdbcTemplate.update(sql, args) == 1;

        String sql2 = "UPDATE bank_accounts SET balance = balance + ? WHERE personal_code = ?";
        Object[] args2 = new Object[]{value, anotherPersonalCode};
        boolean result2 = jdbcTemplate.update(sql2, args2) == 1;

        return result && result2;
    }

    // @Override
    public boolean openAccount(String personalCode, int value) {
        String sql = "UPDATE bank_accounts SET balance = ? WHERE personal_code = ?";
        Object[] args = new Object[]{value, personalCode};
        return jdbcTemplate.update(sql, args) == 1;
    }

    // @Override
    public boolean closeAccount(String personalCode) {
        String sql = "UPDATE bank_accounts SET balance = ? WHERE personal_code = ?";
        Object[] args = new Object[]{null, personalCode};
        return jdbcTemplate.update(sql, args) == 1;
    }

    // @Override
    public Optional<BankAccount> seeYourAccount(String personalCode) {
        String sql = "SELECT * FROM bank_accounts WHERE personal_code = ?";
        Object[] args = new Object[]{personalCode};
        List<BankAccount> bankAccount = jdbcTemplate.query(sql, args, new BankAccountRowMapper());
        return Optional.of(bankAccount.get(0));
    }
}