package myApp.core.responses;


import myApp.core.domain.BankAccount;
import java.util.List;



public class GetAllBankAccountsResponse extends CoreResponse {

    private List<BankAccount> bankAccounts;

    public GetAllBankAccountsResponse(List<CoreError> errors, List<BankAccount> bankAccounts) {
        super(errors);
        this.bankAccounts = bankAccounts;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
