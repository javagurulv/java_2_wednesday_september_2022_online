package myApp.core.requests;

public class RemoveBankAccountRequest {

    private String personalCode;

    public RemoveBankAccountRequest(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getPersonalCode() {
        return personalCode;
    }
}
