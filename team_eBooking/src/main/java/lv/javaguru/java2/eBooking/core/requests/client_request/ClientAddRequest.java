package lv.javaguru.java2.eBooking.core.requests.client_request;

import lombok.Data;

@Data
public class ClientAddRequest {
    private String clientEmail;
    private String clientPhoneNumber;

    public ClientAddRequest() {
    }

    public ClientAddRequest(String clientEmail, String clientPhoneNumber) {
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;
    }
}
