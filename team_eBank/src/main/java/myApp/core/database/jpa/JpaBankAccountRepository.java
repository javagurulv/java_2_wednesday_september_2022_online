package myApp.core.database.jpa;

import myApp.core.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaBankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Modifying
    @Query(value = "update bank_accounts\n" +
            "set balance = CASE \n" +
            "  WHEN personal_code = ?1\n" +
            "    THEN balance - ?3\n" +
            "  WHEN personal_code = ?2\n" +
            "    THEN balance + ?3\n" +
            "    End;", nativeQuery = true)
    void bankTransfer(String personalCode, String anotherPersonalCode
            , int value);

    void deleteByPersonalCode(String personalCode);

    @Query("SELECT b FROM BankAccount b WHERE b.personalCode=?1")
    Optional<BankAccount> seeYourAccount(String personalCode);

    @Modifying
    @Query("Update BankAccount b set b.balance = null where b.personalCode = ?1")
    void closeAccount(String personalCode);

    @Modifying
    @Query("Update BankAccount b set b.balance = 0 where b.personalCode = ?1")
    void openAccount(String personalCode);

    List<BankAccount> findByName(String name);

    List<BankAccount> findBySurname(String surname);

    List<BankAccount> findByNameAndSurname(String name, String surname);

    List<BankAccount> findByPersonalCode(String personalCode);

    List<BankAccount> findByNameAndPersonalCode(String name, String personalCode);

    List<BankAccount> findBySurnameAndPersonalCode(String surname, String personalCode);

    List<BankAccount> findByNameAndSurnameAndPersonalCode(String name, String surname, String personalCode);
}