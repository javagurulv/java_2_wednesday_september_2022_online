package lv.javaguru.java2.tasksScheduler.requests;

public class AddSettingsRequest {

    private String adminPassword;
    private String emailFrom;
    private String emailPassword;
    private String emailHost;
    private String emailPort;
    private String emailProtocol;

    public AddSettingsRequest(String adminPassword, String emailFrom, String emailPassword, String emailHost, String emailPort, String emailProtocol) {
        this.adminPassword = adminPassword;
        this.emailFrom = emailFrom;
        this.emailPassword = emailPassword;
        this.emailHost = emailHost;
        this.emailPort = emailPort;
        this.emailProtocol = emailProtocol;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public String getEmailPort() {
        return emailPort;
    }

    public String getEmailProtocol() {
        return emailProtocol;
    }
}
