package lv.javaguru.java2.atmapp.responses.adminResponses;

import lv.javaguru.java2.atmapp.Accounts;

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
