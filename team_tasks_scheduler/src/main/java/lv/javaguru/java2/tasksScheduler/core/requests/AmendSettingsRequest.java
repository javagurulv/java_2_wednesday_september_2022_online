package lv.javaguru.java2.tasksScheduler.core.requests;

import lv.javaguru.java2.tasksScheduler.core.domain.Settings;

public class AmendSettingsRequest {

    private String adminPassword;
    private String emailFrom;
    private String emailPassword;
    private String emailHost;
    private String emailPort;
    private String emailProtocol;

    private String sessionId;

    public AmendSettingsRequest() {
    }

    public AmendSettingsRequest(String adminPassword, String emailFrom, String emailPassword,
                                String emailHost, String emailPort, String emailProtocol) {
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

    public AmendSettingsRequest(Settings settings, String sessionId) {
        this(settings);
        this.sessionId = sessionId;
    }

    public AmendSettingsRequest(String adminPassword, String emailFrom,
                                String emailPassword, String emailHost,
                                String emailPort, String emailProtocol, String sessionId) {
        this(adminPassword, emailFrom, emailPassword, emailHost, emailPort, emailProtocol);
        this.sessionId = sessionId;
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

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public void setEmailPort(String emailPort) {
        this.emailPort = emailPort;
    }

    public void setEmailProtocol(String emailProtocol) {
        this.emailProtocol = emailProtocol;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
