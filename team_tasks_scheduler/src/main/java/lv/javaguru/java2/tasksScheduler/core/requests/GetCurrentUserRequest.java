package lv.javaguru.java2.tasksScheduler.core.requests;

public class GetCurrentUserRequest {
    private boolean decryptedPassword;
    private String webSessionId;

    public GetCurrentUserRequest() {
        webSessionId = null;
    }
    public GetCurrentUserRequest(String webSessionId) {
        this.webSessionId = webSessionId;
    }

    public GetCurrentUserRequest(boolean decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    public GetCurrentUserRequest(String webSessionId, boolean decryptedPassword) {
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
