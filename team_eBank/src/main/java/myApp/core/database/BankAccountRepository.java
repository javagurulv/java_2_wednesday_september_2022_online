package myApp.core.database;

import myApp.core.domain.BankAccount;
import myApp.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository {

    void addBankAccount(BankAccount bankAccount,User user);

    boolean deleteBankAccount(String personalCode);

    List<BankAccount> getAllBankAccounts();
    List<User> getAllUsers();
    boolean bankTransfer(String personalCode, String anotherPersonalCode, int value);

    boolean openAccount(String personalCode);

    boolean closeAccount(String personalCode);

   Optional<BankAccount> seeYourAccount(String personalCode);

    List<BankAccount> findByName(String name);
    List<BankAccount> findBySurname(String surname);
    List<BankAccount> findByPersonalCode(String personalCode);
    List<BankAccount> findByNameAndSurname(String name, String surname);
    List<BankAccount> findByNameAndPersonalCode(String name, String personalCode );
    List<BankAccount> findBySurnameAndPersonalCode(String surname, String personalCode );
    List<BankAccount> findByNameAndSurnameAndPersonalCode(String name,String surname, String personalCode );
}