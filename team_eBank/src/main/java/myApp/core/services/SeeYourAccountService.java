package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.domain.BankAccount;
import myApp.core.requests.SeeYourAccountRequest;
import myApp.core.responses.SeeYourAccountResponse;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

import java.util.Optional;
@DIComponent
public class SeeYourAccountService {
    @DIDependency
    private DataBase dataBase;

    public SeeYourAccountResponse execute(SeeYourAccountRequest request) {
        Optional<BankAccount> bankAccount = dataBase.seeYourAccount(request.getPersonalCode());
        return new SeeYourAccountResponse(bankAccount);
    }
}
