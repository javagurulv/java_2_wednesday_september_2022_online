package lv.javaguru.java2.eBooking.core.response;

import lv.javaguru.java2.eBooking.Client;

public class AddClientResponse {

    private Client newClient;

    public AddClientResponse(Client newClient) {
        this.newClient = newClient;
    }

    public Client getNewClient() {
        return newClient;
    }
}
