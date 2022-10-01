package eBooking.response;

import eBooking.Client;

public class AddClientResponse {

    private Client newClient;

    public AddClientResponse(Client newClient) {
        this.newClient = newClient;
    }

    public Client getNewClient() {
        return newClient;
    }
}
