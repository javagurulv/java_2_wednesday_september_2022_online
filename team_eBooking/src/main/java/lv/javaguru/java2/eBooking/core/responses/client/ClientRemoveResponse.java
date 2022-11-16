package lv.javaguru.java2.eBooking.core.responses.client;

import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.CoreResponse;

import java.util.List;

public class ClientRemoveResponse extends CoreResponse {

    private boolean clientRemoved;

    public ClientRemoveResponse(List<CoreError> errors) {
        super(errors);
    }

    public ClientRemoveResponse(boolean clientRemoved) {
        this.clientRemoved = clientRemoved;
    }

    public boolean isClientRemoved() {
        return clientRemoved;
    }
}
