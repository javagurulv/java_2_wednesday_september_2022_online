package lv.javaguru.java2.tasksScheduler.core.requests;

public class GetSettingsRequest {
    private boolean decryptedPassword;
    private String sessionId;

    public GetSettingsRequest(boolean decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    public GetSettingsRequest(boolean decryptedPassword, String sessionId) {
        this(decryptedPassword);
        this.sessionId = sessionId;
    }

    public boolean isDecryptedPassword() {
        return decryptedPassword;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
