package myApp.core.requests;

public class CloseAccountRequest {

    private String personalCode;

    public CloseAccountRequest(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getPersonalCode() {
        return personalCode;
    }
}
