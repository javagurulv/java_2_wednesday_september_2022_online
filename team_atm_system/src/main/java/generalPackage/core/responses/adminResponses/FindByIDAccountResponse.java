package generalPackage.core.responses.adminResponses;

import generalPackage.core.domain.Accounts;

import java.util.List;

public class FindByIDAccountResponse  extends CoreResponse {

private Accounts accountToFind;

    public FindByIDAccountResponse(Accounts accountToFind) {
        this.accountToFind = accountToFind;
    }

    public Accounts getAccountByID() {
        return accountToFind;
    }

    public FindByIDAccountResponse (List <CoreError> errors){
        super(errors);
    }
}
