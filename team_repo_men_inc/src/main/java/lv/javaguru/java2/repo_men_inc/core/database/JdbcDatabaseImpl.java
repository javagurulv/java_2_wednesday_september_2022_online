package lv.javaguru.java2.repo_men_inc.core.database;

import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class JdbcDatabaseImpl implements Database {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean save(Debtor debtor) {
        return jdbcTemplate.update("INSERT INTO debtors  (name) VALUES (?)", debtor.getName()) == 1;
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM debtors WHERE debtors.id = ?", id) == 1;
    }

    @Override
    public Debtor getById(Long id) {
        return jdbcTemplate.query("SELECT debtors.id, debtors.name, items.item_name FROM debtors" +
                        " LEFT JOIN lists ON debtors.id = lists.debtor_id" +
                        " LEFT JOIN items ON lists.item_id = items.id WHERE debtors.id = ?", new DebtorMapper(), id).stream()
                .filter(debtor -> debtor.getId().equals(id))
                .findAny().orElse(null);
    }

    @Override
    public Debtor getByName(String name) {
        return jdbcTemplate.query("SELECT debtors.id, debtors.name, items.item_name FROM debtors" +
                        " LEFT JOIN lists ON debtors.id = lists.debtor_id" +
                        " LEFT JOIN items ON lists.item_id = items.id" +
                        " WHERE debtors.name = ?", new DebtorMapper(), name).stream()
                .filter(debtor -> debtor.getName().equals(name))
                .findAny().orElse(null);
    }

    @Override
    public Debtor getByNameAndListItem(String name, String listItem) {
        return jdbcTemplate.query("SELECT debtors.id, debtors.name, items.item_name FROM debtors" +
                        " LEFT JOIN lists ON debtors.id = lists.debtor_id" +
                        " LEFT JOIN items ON lists.item_id = items.id" +
                        " WHERE debtors.name = ? and items.item_name = ?", new DebtorMapper(), name, listItem).stream()
                .findAny().orElse(null);
    }

    @Override
    public List<Debtor> getByListItem(String listItem) {
        return jdbcTemplate.query("SELECT debtors.id, debtors.name, items.item_name FROM debtors" +
                " LEFT JOIN lists ON debtors.id = lists.debtor_id" +
                " LEFT JOIN items ON lists.item_id = items.id" +
                " WHERE items.item_name = ?", new DebtorMapper(), listItem);
    }

    @Override
    public List<Debtor> getAllDebtors() {
        return jdbcTemplate.query("SELECT debtors.id, debtors.name, items.item_name from debtors" +
                " LEFT JOIN lists ON debtors.id = lists.debtor_id" +
                " LEFT JOIN items ON lists.item_id = items.id", new DebtorMapper());
    }

    @Override
    public BigInteger saveItem(String itemName) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO items (item_name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, itemName);
            return preparedStatement;
        }, keyHolder);
        return (BigInteger) keyHolder.getKey();
    }

    @Override
    public int updateList(BigInteger itemId, long debtorId) {
        return jdbcTemplate.update("INSERT INTO LISTS (debtor_id, item_id) VALUES (?, ?)", debtorId, itemId);
    }

    @Override
    public BigInteger saveDebtorAndReturnId(long index, String debtorName) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO debtors (id, name) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, index);
            preparedStatement.setString(2, debtorName);
            return preparedStatement;
        }, keyHolder);
        return (BigInteger) keyHolder.getKey();
    }
}
