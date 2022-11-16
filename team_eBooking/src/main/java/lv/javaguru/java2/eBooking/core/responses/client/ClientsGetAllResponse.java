package lv.javaguru.java2.eBooking.core.responses.client;

import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.responses.CoreResponse;

import java.util.List;

public class ClientsGetAllResponse extends CoreResponse {

    private List<Client> clients;

    public ClientsGetAllResponse(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }
}
