package generalPackage.core.responses.adminResponses;

import generalPackage.Accounts;

import java.util.List;

public class AddAccountResponse extends CoreResponse {

    private Accounts newAccount;

    public AddAccountResponse(Accounts newAccount) {
        this.newAccount = newAccount;
    }

    public Accounts getNewAccount() {
        return newAccount;
    }

    public AddAccountResponse (List <CoreError> errors){
        super(errors);
    }
}
