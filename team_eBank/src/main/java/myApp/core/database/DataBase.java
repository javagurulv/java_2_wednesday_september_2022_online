package myApp.core.database;

import myApp.BankAccount;

import java.util.List;

public interface DataBase {

    void addBankAccount(BankAccount bankAccount);

    boolean deleteBankAccount(Long id);

    List<BankAccount> getAllBankAccounts();

    boolean bankTransfer(Long userID, int value, Long anotherAccountID);
}
