package lv.javaguru.java2.tasksScheduler.requests;

import lv.javaguru.java2.tasksScheduler.domain.User;

public class AmendCurrentUserRequest {

    private String username;
    private String password;
    private String email;
    private boolean sendReminders;

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
}
