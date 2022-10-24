package generalPackage.core.responses.adminResponses;

import generalPackage.Accounts;

import java.util.List;

public class GetAllAccountsResponse {

private List<Accounts> accounts;

    public GetAllAccountsResponse(List<Accounts> accounts) {
        this.accounts = accounts;
    }

    public List<Accounts> getAccounts() {
        return accounts;
    }
}
