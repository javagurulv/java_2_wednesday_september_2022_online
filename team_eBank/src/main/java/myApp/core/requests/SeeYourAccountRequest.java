package myApp.core.requests;

import myApp.core.domain.Account;

public class SeeYourAccountRequest {

    private String personalCode;

    public SeeYourAccountRequest(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getPersonalCode() {
        return personalCode;
    }
}
