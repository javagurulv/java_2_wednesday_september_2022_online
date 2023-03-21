package lv.javaguru.java2.tasksScheduler.core.requests;

public class CheckLoggedUserRequest {

    private String sessionId;

    public CheckLoggedUserRequest(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
