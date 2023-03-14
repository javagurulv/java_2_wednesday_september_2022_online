package lv.javaguru.java2.tasksScheduler.core.requests;

import lv.javaguru.java2.tasksScheduler.core.domain.User;

public class AmendCurrentUserRequest {

    private String username;
    private String password;
    private String email;
    private boolean sendReminders;
    private String sessionId = null;

    public AmendCurrentUserRequest() {
    }

    public AmendCurrentUserRequest(String username, String password, String email, boolean sendReminders) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.sendReminders = sendReminders;
    }

    public AmendCurrentUserRequest(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.sendReminders = user.isSendReminders();
    }

    public AmendCurrentUserRequest(User user, String sessionId) {
        this(user);
        this.sessionId = sessionId;
    }

    public AmendCurrentUserRequest(String username, String password,
                                   String email, boolean sendReminders,
                                   String sessionId) {
        this(username, password, email, sendReminders);
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isSendReminders() {
        return sendReminders;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSendReminders(boolean sendReminders) {
        this.sendReminders = sendReminders;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
