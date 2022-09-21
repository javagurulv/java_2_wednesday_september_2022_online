package myApp.core.responses;

import myApp.BankAccount;

public class AddAccountResponse {

    private BankAccount bankAccount;

    public AddAccountResponse(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
