package lv.javaguru.java2.tasksScheduler.requests;

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
