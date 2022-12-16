package lv.javaguru.java2.tasksScheduler.core.requests;

public class GetCurrentUserRequest {
    boolean decryptedPassword;

    public GetCurrentUserRequest() {
    }

    public GetCurrentUserRequest(boolean decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    public boolean isDecryptedPassword() {
        return decryptedPassword;
    }

    public void setDecryptedPassword(boolean decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }
}
