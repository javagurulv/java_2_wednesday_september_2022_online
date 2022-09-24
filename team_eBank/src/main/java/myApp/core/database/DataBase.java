package myApp.core.database;

import myApp.BankAccount;

import java.util.Map;

public interface DataBase {

    void addBankAccount(BankAccount bankAccount);

    boolean deleteBankAccount(String personalCode);

    Map<String , BankAccount> getAllBankAccountsMap();

    boolean bankTransfer(String personalCode, String anotherPersonalCode, int value);

    BankAccount openAccount(String personalCode);
    boolean closeAccount(String personalCode);
   BankAccount seeYourAccount(String personalCode);
   String  logIn(String personalCode);

}
