package lv.javaguru.java2.tasksScheduler.core.requests;

public class ExitSettingsRequest {

    public String sessionId;
    public ExitSettingsRequest() {
    }

    public ExitSettingsRequest(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
