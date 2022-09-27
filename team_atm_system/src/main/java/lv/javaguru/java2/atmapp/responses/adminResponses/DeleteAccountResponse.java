package lv.javaguru.java2.atmapp.responses.adminResponses;

public class DeleteAccountResponse {

    private boolean accountToDelete;

    public DeleteAccountResponse(boolean accountToDelete) {
        this.accountToDelete = accountToDelete;
    }

    public boolean isAccountToDelete() {
        return accountToDelete;
    }
}
