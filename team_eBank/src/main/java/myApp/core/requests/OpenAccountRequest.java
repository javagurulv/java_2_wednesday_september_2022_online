package myApp.core.requests;

public class OpenAccountRequest {

    private String personalCode;

    public OpenAccountRequest(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getPersonalCode() {
        return personalCode;
    }
}
