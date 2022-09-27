package lv.javaguru.java2.atmapp.requests;

public class DeleteAccountRequest {
    private Long accountToDelete;

    public DeleteAccountRequest(Long accountToDelete) {
        this.accountToDelete = accountToDelete;
    }

    public Long getAccountToDelete() {
        return accountToDelete;
    }
}
