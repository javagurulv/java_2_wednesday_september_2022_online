package myApp.core.responses;

import myApp.core.domain.BankAccount;

import java.util.List;
import java.util.Map;

public class GetAllBankAccountsResponse extends CoreResponse {

    private List<BankAccount> bankAccounts;

    public GetAllBankAccountsResponse(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
