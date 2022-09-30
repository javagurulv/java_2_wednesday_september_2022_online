package myApp.core.requests;

public class LogInRequest {

    private String personalCode;
    private String password;

    public LogInRequest(String personalCode, String password) {
        this.personalCode = personalCode;
        this.password = password;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public String getPassword() {
        return password;
    }
}
