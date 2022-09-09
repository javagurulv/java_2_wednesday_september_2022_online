package myApp.core.responses;

import myApp.BankAccount;

import java.util.List;

public class GetAllBankAccountsResponse extends CoreResponse {

    List<BankAccount> bankAccounts;

    public GetAllBankAccountsResponse(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
