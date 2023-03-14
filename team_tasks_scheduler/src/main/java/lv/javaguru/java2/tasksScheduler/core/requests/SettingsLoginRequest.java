package lv.javaguru.java2.tasksScheduler.core.requests;

public class SettingsLoginRequest {
    private String adminPassword;
    private String sessionId;

    public SettingsLoginRequest() {
    }

    public SettingsLoginRequest(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
