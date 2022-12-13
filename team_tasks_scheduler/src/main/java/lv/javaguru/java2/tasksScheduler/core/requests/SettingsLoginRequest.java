package lv.javaguru.java2.tasksScheduler.core.requests;

public class SettingsLoginRequest {
    private String adminPassword;

    public SettingsLoginRequest(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminPassword() {
        return adminPassword;
    }
}
