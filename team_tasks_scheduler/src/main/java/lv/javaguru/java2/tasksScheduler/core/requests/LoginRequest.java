package lv.javaguru.java2.tasksScheduler.core.requests;

public class LoginRequest {
    private String userName;
    private String password;

    private String webSessionId;

    public LoginRequest() { }

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.webSessionId = null;
    }
    public LoginRequest(String userName, String password, String webSessionId) {
        this(userName, password);
        this.webSessionId = webSessionId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebSessionId() {
        return webSessionId;
    }

    public void setWebSessionId(String webSessionId) {
        this.webSessionId = webSessionId;
    }
}
