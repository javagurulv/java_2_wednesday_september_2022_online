package generalPackage.adminResponses;

public class DeleteAccountResponse {

    private boolean accountDeleted;

    public DeleteAccountResponse(boolean accountDeleted) {
        this.accountDeleted = accountDeleted;
    }

    public boolean isAccountDeleted() {
        return accountDeleted;
    }
}
