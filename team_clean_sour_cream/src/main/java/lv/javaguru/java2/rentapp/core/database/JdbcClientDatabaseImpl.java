package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.core.database.row_mappers.ClientRowMapper;
import lv.javaguru.java2.rentapp.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JdbcClientDatabaseImpl implements ClientDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Client client) {
        if (findByPersonalId(client.getPersonalId()).isPresent()) {
            return findByPersonalId(client.getPersonalId()).get().getId();
        } else if (findByEmail(client.getEmail()).isPresent()) {
            return findByEmail(client.getEmail()).get().getId();
        }

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("clients").usingGeneratedKeyColumns("id");
        Map<String, Object> clientArgs = new HashMap<>();
        clientArgs.put("personal_id", client.getPersonalId());
        clientArgs.put("name", client.getFirstName());
        clientArgs.put("surname", client.getLastName());
        clientArgs.put("email", client.getEmail());
        clientArgs.put("phone", client.getPhoneNumber());
        return simpleJdbcInsert.executeAndReturnKey(clientArgs).longValue();
    }

    @Override
    public Optional<Client> getById(Long id) {
        String sql = "SELECT * FROM clients WHERE id = ?";
        List<Client> client = jdbcTemplate.query(sql, new ClientRowMapper(), id);
        return client.stream().findFirst();
    }

    @Override
    public Optional<Client> findByPersonalId(String personalId) {
        String sql = "SELECT * FROM clients WHERE personal_id = ?";
        List<Client> clients = jdbcTemplate.query(sql, new ClientRowMapper(), personalId);
        return clients.stream().findFirst();
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        String sql = "SELECT * FROM clients WHERE email = ?";
        List<Client> clients = jdbcTemplate.query(sql, new ClientRowMapper(), email);
        return clients.stream().findFirst();
    }

    @Override
    public Optional<Client> findByFirstName(String firstName) {
        String sql = "SELECT * FROM clients WHERE name = ?";
        List<Client> clients = jdbcTemplate.query(sql, new ClientRowMapper(), firstName);
        return clients.stream().findFirst();
    }

    @Override
    public Optional<Client> findByLastName(String lastName) {
        String sql = "SELECT * FROM clients WHERE name = ?";
        List<Client> clients = jdbcTemplate.query(sql, new ClientRowMapper(), lastName);
        return clients.stream().findFirst();
    }
}
