package lv.javaguru.java2.tasksScheduler.domain;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Settings {

    private String adminPassword;
    private String emailFrom;
    private String emailPassword;
    private String emailHost;
    private String emailPort;
    private String emailProtocol;

    public Settings() {
    }

    public Settings(String adminPassword, String emailFrom, String emailPassword, String emailHost, String emailPort, String emailProtocol) {
        this.adminPassword = adminPassword;
        this.emailFrom = emailFrom;
        this.emailPassword = emailPassword;
        this.emailHost = emailHost;
        this.emailPort = emailPort;
        this.emailProtocol = emailProtocol;
    }

    public Settings(Settings settings) {
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

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public String getEmailPort() {
        return emailPort;
    }

    public void setEmailPort(String emailPort) {
        this.emailPort = emailPort;
    }

    public String getEmailProtocol() {
        return emailProtocol;
    }

    public void setEmailProtocol(String emailProtocol) {
        this.emailProtocol = emailProtocol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settings settings = (Settings) o;
        return Objects.equals(adminPassword, settings.adminPassword) && Objects.equals(emailFrom, settings.emailFrom) && Objects.equals(emailPassword, settings.emailPassword) && Objects.equals(emailHost, settings.emailHost) && Objects.equals(emailPort, settings.emailPort) && Objects.equals(emailProtocol, settings.emailProtocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminPassword, emailFrom, emailPassword, emailHost, emailPort, emailProtocol);
    }

    @Override
    public String toString() {
        return "Settings{" +
                "adminPassword='" + adminPassword + '\'' +
                ", emailFrom='" + emailFrom + '\'' +
                ", emailPassword='" + emailPassword + '\'' +
                ", emailHost='" + emailHost + '\'' +
                ", emailPort='" + emailPort + '\'' +
                ", emailProtocol='" + emailProtocol + '\'' +
                '}';
    }
}
