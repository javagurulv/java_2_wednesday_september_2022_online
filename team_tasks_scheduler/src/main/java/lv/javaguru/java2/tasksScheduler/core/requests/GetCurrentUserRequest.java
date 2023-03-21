package lv.javaguru.java2.tasksScheduler.core.requests;

public class GetCurrentUserRequest {
    private boolean decryptedPassword;
    private String webSessionId;

    public GetCurrentUserRequest(boolean decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    public GetCurrentUserRequest(boolean decryptedPassword, String webSessionId) {
        this(decryptedPassword);
        this.webSessionId = webSessionId;
    }

    public boolean isDecryptedPassword() {
        return decryptedPassword;
    }

    public void setDecryptedPassword(boolean decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    public String getWebSessionId() {
        return webSessionId;
    }

    public void setWebSessionId(String webSessionId) {
        this.webSessionId = webSessionId;
    }
}
