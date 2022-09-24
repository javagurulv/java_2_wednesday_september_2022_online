package myApp.core.responses;

public class CloseAccountResponse {

    private boolean isDeleted;

    public CloseAccountResponse(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
