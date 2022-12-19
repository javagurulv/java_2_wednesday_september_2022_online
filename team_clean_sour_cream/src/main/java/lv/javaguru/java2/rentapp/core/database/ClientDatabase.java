package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.domain.Client;

import java.util.Optional;

public interface ClientDatabase {
    Optional<Client> getById(Long id);
    Long save(Client client);
}
