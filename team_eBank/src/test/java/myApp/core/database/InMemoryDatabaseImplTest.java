package myApp.core.database;

import myApp.core.domain.Account;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class InMemoryDatabaseImplTest {

    DataBase dataBase;

    @BeforeEach
    void setUp() {
        dataBase = new InMemoryDatabaseImpl();
    }

    @Test
    void addBankAccount() {
        BankAccount bankAccount = new BankAccount("Example", "Example","password", Roles.Regular_user
                , "111-000");
        dataBase.addBankAccount(bankAccount);
        assertTrue(dataBase.getAllBankAccounts().contains(bankAccount));

    }

    @Test
    void deleteBankAccount() {
        BankAccount bankAccount = new BankAccount("Example", "Example","password", Roles.Regular_user
                , "111-000");
        dataBase.deleteBankAccount("111-000");
        assertFalse(dataBase.getAllBankAccounts().contains(bankAccount));
    }

    @Test
    void bankTransfer() {
        BankAccount bankAccount = new BankAccount("Example1", "Example1","password", Roles.Regular_user
                , "111-000");
        bankAccount.setAccount(new Account(1L, 1000));
        BankAccount bankAccountTwo = new BankAccount("Example2", "Example2","password", Roles.Regular_user
                , "122-000");
        dataBase.addBankAccount(bankAccount);
        dataBase.addBankAccount(bankAccountTwo);
        bankAccountTwo.setAccount(new Account(2L, 1000));
        assertTrue(dataBase.bankTransfer("111-000", "122-000", 100));
    }

    @Test
    void openAccount() {
        BankAccount bankAccount = new BankAccount("Example1", "Example1","password", Roles.Regular_user
                , "111-000");
        dataBase.addBankAccount(bankAccount);
        assertTrue(dataBase.openAccount("111-000"));
    }

    @Test
    void closeAccount() {
        BankAccount bankAccount = new BankAccount("Example1", "Example1","password", Roles.Regular_user
                , "111-000");
        dataBase.addBankAccount(bankAccount);
        bankAccount.setAccount(new Account(1L, 0));
        boolean result = dataBase.closeAccount("111-000");
        assertTrue(result);
    }

    @Test
    void getAllBankAccountsMap() {
        assertNotNull(dataBase.getAllBankAccounts());
    }

    @Test
    void seeYourAccount() {
        BankAccount bankAccount = new BankAccount("Example1", "Example1","password", Roles.Regular_user
                , "111-000");
        dataBase.addBankAccount(bankAccount);
        assertNotNull(dataBase.seeYourAccount("111-000"));
    }
}