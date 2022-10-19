package lv.javaguru.java2.eBooking.core.requests.client_request;

public class AddClientRequest {
    private String clientEmail;
    private String clientPhoneNumber;

    public AddClientRequest(String clientEmail, String clientPhoneNumber) {
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }
}
