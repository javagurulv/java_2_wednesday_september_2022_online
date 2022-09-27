package lv.javaguru.java2.atmapp.responses;

import lv.javaguru.java2.atmapp.Accounts;

public class AddAccountResponse {

    private Accounts accounts;

    public AddAccountResponse(Accounts accounts) {
        this.accounts = accounts;
    }

    public Accounts getAccounts() {
        return accounts;
    }
}
