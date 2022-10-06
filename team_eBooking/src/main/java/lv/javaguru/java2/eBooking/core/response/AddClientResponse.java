package lv.javaguru.java2.eBooking.core.response;

import lv.javaguru.java2.eBooking.Client;

import java.util.List;

public class AddClientResponse extends CoreResponse{

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
