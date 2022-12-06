package lv.javaguru.java2.tasksScheduler.core.requests;

public class GetCurrentUserRequest {
    boolean decryptedPassword;

    public GetCurrentUserRequest(boolean decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    public boolean isDecryptedPassword() {
        return decryptedPassword;
    }
}
