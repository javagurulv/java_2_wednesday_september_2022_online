package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.domain.Client;

import java.util.Optional;

public interface ClientDatabase {
    Optional<Client> getById(Long id);

    Long save(Client client);

    Optional<Client> findByPersonalId(String personalId);

    Optional<Client> findByEmail(String email);

    Optional<Client> findByFirstName(String firstName);

    Optional<Client> findByLastName(String lastName);

}