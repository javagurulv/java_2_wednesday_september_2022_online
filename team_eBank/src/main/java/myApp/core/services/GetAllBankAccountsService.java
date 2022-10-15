package myApp.core.services;

import myApp.core.domain.BankAccount;
import myApp.core.database.DataBase;
import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class GetAllBankAccountsService {
    @DIDependency
    private DataBase dataBase;

    public GetAllBankAccountsResponse execute(GetAllBankAccountsRequest request) {
        List<BankAccount> bankAccounts = dataBase.getAllBankAccounts();
        return new GetAllBankAccountsResponse(null, bankAccounts);
    }
}
