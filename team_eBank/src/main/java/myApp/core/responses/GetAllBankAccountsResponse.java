package myApp.core.responses;

import myApp.BankAccount;

import java.util.Map;

public class GetAllBankAccountsResponse extends CoreResponse {

    private Map<String, BankAccount> bankAccounts;

    public GetAllBankAccountsResponse(Map<String, BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public Map<String, BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
