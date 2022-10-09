package lv.javaguru.java2.eBooking.core.responses.client_response;

import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.responses.CoreResponse;

import java.util.List;

public class GetAllClientsResponse extends CoreResponse {

    private List<Client> clients;

    public GetAllClientsResponse(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }
}
