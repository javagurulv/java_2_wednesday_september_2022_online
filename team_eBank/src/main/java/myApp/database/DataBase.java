package myApp.database;

import myApp.BankAccount;

import java.util.List;

public interface DataBase {

    void addBankAccount(BankAccount bankAccount);

    void deleteBankAccount(Long id);

    List<BankAccount> getAllBankAccounts();

    void  bankTransfer(Long userID, int value, Long anotherAccountID);
}
