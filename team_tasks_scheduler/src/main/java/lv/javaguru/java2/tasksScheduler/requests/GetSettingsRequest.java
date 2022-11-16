package lv.javaguru.java2.tasksScheduler.requests;

public class GetSettingsRequest {
    boolean decryptedPassword;

    public GetSettingsRequest(boolean decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    public boolean isDecryptedPassword() {
        return decryptedPassword;
    }
}
