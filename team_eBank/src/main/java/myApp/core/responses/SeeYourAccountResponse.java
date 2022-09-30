package myApp.core.responses;

import myApp.core.domain.BankAccount;

import java.util.Optional;

public class SeeYourAccountResponse {

    private Optional<BankAccount> bankAccount;

    public SeeYourAccountResponse(Optional<BankAccount> bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Optional<BankAccount> getBankAccount() {
        return bankAccount;
    }
}
