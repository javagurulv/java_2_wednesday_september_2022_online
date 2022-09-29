package myApp.core.database;

import myApp.core.domain.BankAccount;

import java.util.List;
import java.util.Optional;

public interface DataBase {

    void addBankAccount(BankAccount bankAccount);

    boolean deleteBankAccount(String personalCode);

    List<BankAccount> getAllBankAccounts();

    boolean bankTransfer(String personalCode, String anotherPersonalCode, int value);

    boolean openAccount(String personalCode);
    boolean closeAccount(String personalCode);
   Optional<BankAccount> seeYourAccount(String personalCode);

}
