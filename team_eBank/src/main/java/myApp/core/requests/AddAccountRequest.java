package myApp.core.requests;

public class AddAccountRequest {

    private String personalCode;

    public AddAccountRequest(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getPersonalCode() {
        return personalCode;
    }
}
