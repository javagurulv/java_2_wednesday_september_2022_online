package myApp;

import java.util.ArrayList;
import java.util.List;

class BankAccountApplication {

    public static void main(String[] args) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        BankAccount bankAccount = new BankAccount("Vlad", 10000, 1234);
        BankAccountService bankAccountService = new BankAccountService(bankAccounts);
    }

}
