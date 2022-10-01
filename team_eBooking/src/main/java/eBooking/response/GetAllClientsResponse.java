package eBooking.response;

import eBooking.Client;

import java.util.List;

public class GetAllClientsResponse {

    private List<Client> clients;

    public GetAllClientsResponse(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }
}
