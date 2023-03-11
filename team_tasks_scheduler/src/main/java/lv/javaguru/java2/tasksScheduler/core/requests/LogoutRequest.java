package lv.javaguru.java2.tasksScheduler.core.requests;

public class LogoutRequest {

    private String sessionId = null;
    public LogoutRequest() {
    }

    public LogoutRequest(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
