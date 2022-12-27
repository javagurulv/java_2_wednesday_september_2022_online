package lv.javaguru.java2.rentapp.core.database.row_mappers;

import lv.javaguru.java2.rentapp.domain.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        client.setId(rs.getLong("id"));
        client.setPersonalId(rs.getString("personal_id"));
        client.setFirstName(rs.getString("name"));
        client.setLastName(rs.getString("surname"));
        client.setEmail(rs.getString("email"));
        client.setPhoneNumber(rs.getString("phone"));
        return client;
    }
}
