package lv.javaguru.java2.tasksScheduler.core.services.system;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SessionService {

    private Long loggedUserId;
    private String decryptedPassword;

    private Map<String, Long> webLoggedUsers = new HashMap<>();
    ;
    private Map<String, String> webDecryptedPasswords = new HashMap<>();
    ;

    public void login(Long userId, String password) {
        this.loggedUserId = userId;
        this.decryptedPassword = password;
    }

    public void logOut() {
        this.loggedUserId = null;
        this.decryptedPassword = "";
    }

    public Long getCurrentUserId() {
        return this.loggedUserId;
    }

    public Long getCurrentUserId(String sessionId) {
        if (sessionId == null) {
            return this.loggedUserId;
        } else {
            return this.webLoggedUsers.get(sessionId);
        }
    }

    public String getDecryptedPassword() { return this.decryptedPassword; }

    public void setDecryptedPassword(String decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    public void setDecryptedPassword(String decryptedPassword, String sessionId) {
        if (sessionId == null) {
            this.decryptedPassword = decryptedPassword;
        } else {
            this.webDecryptedPasswords.put(sessionId, decryptedPassword);
        }
    }

    public void webLogin(String  sessionId, Long userId, String pwd) {
        this.webLoggedUsers.put(sessionId, userId);
        this.webDecryptedPasswords.put(sessionId, pwd);
    }

    public void webLogout(String sessionId) {
        this.webLoggedUsers.remove(sessionId);
        this.webDecryptedPasswords.remove(sessionId);
    }

    public Long webGetCurrentUserId(String sessionId) {
        return this.webLoggedUsers.get(sessionId);
    }

    public void webSetDecryptedPassword(String sessionId, String decryptedPassword) {
        this.webDecryptedPasswords.put(sessionId, decryptedPassword);
    }

    public String webGetDecryptedPassword(String sessionId) {
        return this.webDecryptedPasswords.get(sessionId);
    }

    public boolean isUserLoggedIn(String sessionId) {
        return this.webLoggedUsers.containsKey(sessionId);
    }
}
