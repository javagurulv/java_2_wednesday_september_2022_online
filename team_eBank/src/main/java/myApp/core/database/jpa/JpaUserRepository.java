package myApp.core.database.jpa;

import liquibase.pro.packaged.S;
import myApp.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {

    void deleteByPersonalCode(String personalCode);

}