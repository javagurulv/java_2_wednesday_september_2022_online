package lv.javaguru.java2.eBooking.core.requests.client_request;


public class SearchClientRequest {
    private String clientEmail;
    private String clientPhoneNumber;


    public SearchClientRequest(String clientEmail, String clientPhoneNumber) {
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;

    }

    public String getClientEmail() {
        return clientEmail;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public boolean isEmailProvided() {
        return this.clientEmail != null && !this.clientEmail.isEmpty();
    }

    public boolean isPhoneNumberProvided(){
        return this.clientPhoneNumber != null && !this.clientPhoneNumber.isEmpty();
    }
}
