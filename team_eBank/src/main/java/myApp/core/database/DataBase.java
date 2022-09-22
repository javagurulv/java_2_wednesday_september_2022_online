package myApp.core.database;

import myApp.BankAccount;

import java.util.List;
import java.util.Map;

public interface DataBase {

    void addBankAccount(BankAccount bankAccount);

    boolean deleteBankAccount(String personalCode);

    Map<String , BankAccount> getAllBankAccountsMap();

    boolean bankTransfer(String personalCode, String anotherPersonalCode, int value);

    BankAccount addAccount(String personalCode);
   BankAccount seeYourAccount(String personalCode);
}
