package lv.javaguru.java2.eBooking.core.responses.client;

import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.CoreResponse;

import java.util.List;

public class AddClientResponse extends CoreResponse {

    private Client newClient;

    public AddClientResponse(List<CoreError> errors){
        super(errors);
    }

    public AddClientResponse(Client newClient) {
        this.newClient = newClient;
    }

    public Client getNewClient() {
        return newClient;
    }
}
