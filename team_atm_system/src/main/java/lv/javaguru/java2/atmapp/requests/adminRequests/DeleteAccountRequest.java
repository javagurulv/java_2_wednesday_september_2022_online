package lv.javaguru.java2.atmapp.requests.adminRequests;

public class DeleteAccountRequest {
    private int accountToDelete;

    public DeleteAccountRequest(int accountToDelete) {
        this.accountToDelete = accountToDelete;
    }

    public int getAccountToDelete() {
        return accountToDelete;
    }
}
