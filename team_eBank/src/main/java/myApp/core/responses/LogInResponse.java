package myApp.core.responses;

public class LogInResponse {

    private String personalCode;

    public LogInResponse(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getPersonalCode() {
        return personalCode;
    }
}
