package myApp.core.responses;

import myApp.core.domain.BankAccount;

public class SeeYourAccountResponse {

    private BankAccount bankAccount;

    public SeeYourAccountResponse(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
