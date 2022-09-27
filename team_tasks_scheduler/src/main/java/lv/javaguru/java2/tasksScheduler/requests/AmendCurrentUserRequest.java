package lv.javaguru.java2.tasksScheduler.requests;

public class AmendCurrentUserRequest {

    private String username;
    private String password;
    private String email;
    private String mobilePhone;

    public AmendCurrentUserRequest(String username, String password, String email, String mobilePhone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobilePhone = mobilePhone;
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

    public String getMobilePhone() {
        return mobilePhone;
    }
}
