package lv.javaguru.java2.repo_men_inc.core.database;

import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DebtorMapper implements ResultSetExtractor<List<Debtor>> {
    @Override
    public List<Debtor> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Debtor> debtors = new ArrayList<>();
        Debtor debtor = null;
        while(rs.next()) {
            if (debtor == null || !debtor.getId().equals(rs.getLong("id"))) {
                debtor = new Debtor();
                debtor.setId(rs.getLong("id"));
                debtor.setName(rs.getString("name"));
                debtors.add(debtor);
            }
            if (rs.getString("item_name") != null) {
                debtor.getList().add(rs.getString("item_name"));
            }
        }
        return debtors;
    }
}
