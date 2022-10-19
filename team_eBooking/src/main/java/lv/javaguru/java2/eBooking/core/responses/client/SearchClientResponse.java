package lv.javaguru.java2.eBooking.core.responses.client;

import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.CoreResponse;

import java.util.List;

public class SearchClientResponse extends CoreResponse {

    private List<Client> clients;

    public SearchClientResponse(List<CoreError> errors, List<Client> clients){
       super(errors);
       this.clients=clients;
   }

    public List<Client> getClients() {
        return clients;
    }
}
