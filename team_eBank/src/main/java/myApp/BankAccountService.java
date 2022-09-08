package myApp;

import java.util.List;
import java.util.stream.Collectors;

class BankAccountService {

    private List<BankAccount> bankAccounts;

    public BankAccountService(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public String getAllBankAccountNames() {
        return bankAccounts.stream()
                .map(BankAccount::getName)
                .sorted()
                .collect(Collectors.joining(","));
    }

    public void putMoney(int userID, double value) {
        bankAccounts.stream()
                .filter(bankAccount -> checkUserID(userID))
                .forEach(bankAccount -> bankAccount
                        .setBalance(bankAccount.getBalance() + value));
    }

    public void withdrawMoney(int userID, double value) {
        bankAccounts.stream()
                .filter(bankAccount -> checkUserID(userID))
                .forEach(bankAccount -> bankAccount
                        .setBalance(bankAccount.getBalance() - value));
    }

    private boolean checkUserID(int userID) {
        return bankAccounts.stream()
                .anyMatch(bankAccount -> bankAccount.getCodeID() == userID);
    }
}