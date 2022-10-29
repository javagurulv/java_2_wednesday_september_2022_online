package lv.javaguru.java2.eBooking.core.responses.client;

import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.CoreResponse;

import java.util.List;

public class ClientSearchResponse extends CoreResponse {

    private List<Client> clients;

    public ClientSearchResponse(List<CoreError> errors, List<Client> clients){
       super(errors);
       this.clients=clients;
   }

    public List<Client> getClients() {
        return clients;
    }
}
