package lv.javaguru.java2.tasksScheduler.requests;

import lv.javaguru.java2.tasksScheduler.domain.Settings;

public class AmendSettingsRequest {

    private String adminPassword;
    private String emailFrom;
    private String emailPassword;
    private String emailHost;
    private String emailPort;
    private String emailProtocol;

    public AmendSettingsRequest(String adminPassword, String emailFrom, String emailPassword, String emailHost, String emailPort, String emailProtocol) {
        this.adminPassword = adminPassword;
        this.emailFrom = emailFrom;
        this.emailPassword = emailPassword;
        this.emailHost = emailHost;
        this.emailPort = emailPort;
        this.emailProtocol = emailProtocol;
    }

    public AmendSettingsRequest(Settings settings) {
        this.adminPassword = settings.getAdminPassword();
        this.emailFrom = settings.getEmailFrom();
        this.emailPassword = settings.getEmailPassword();
        this.emailHost = settings.getEmailHost();
        this.emailPort = settings.getEmailPort();
        this.emailProtocol = settings.getEmailProtocol();
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
