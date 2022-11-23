package generalPackage.core.responses.adminResponses;

import generalPackage.core.domain.Accounts;

import java.util.List;

public class GetAllAccountsResponse extends CoreResponse {

    private List<Accounts> accounts;

    public GetAllAccountsResponse(List<Accounts> accounts, List<CoreError> errors) {
        super(errors);
        this.accounts = accounts;
    }

    public List<Accounts> getAccounts() {
        return accounts;
    }
}
