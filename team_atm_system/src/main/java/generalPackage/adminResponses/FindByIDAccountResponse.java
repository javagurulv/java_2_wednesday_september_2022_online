package generalPackage.adminResponses;

import generalPackage.Accounts;

public class FindByIDAccountResponse {

private Accounts accountToFind;

    public FindByIDAccountResponse(Accounts accountToFind) {
        this.accountToFind = accountToFind;
    }

    public Accounts isAccountFound() {
        return accountToFind;
    }
}
