package lv.javaguru.java2.tasksScheduler.core.services.system;


import org.springframework.stereotype.Component;

@Component
public class SessionService {

    private Long loggedUserId;
    private String decryptedPassword;

    public void login(Long userId, String password) {
        this.loggedUserId = userId;
        this.decryptedPassword = password;
    }

    public void logOut() {
        this.loggedUserId = 0L;
        this.decryptedPassword = "";
    }

    public Long getCurrentUserId() {
        return this.loggedUserId;
    }
    public String getDecryptedPassword() { return this.decryptedPassword; }

    public void setDecryptedPassword(String decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }
}
