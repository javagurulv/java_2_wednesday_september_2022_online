package generalPackage.adminResponses;

import generalPackage.Accounts;

public class AddAccountResponse {

    private Accounts newAccount;

    public AddAccountResponse(Accounts newAccount) {
        this.newAccount = newAccount;
    }

    public Accounts getNewAccount() {
        return newAccount;
    }
}
