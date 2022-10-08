package lv.javaguru.java2.eBooking.core.response;

import java.util.List;

public class RemoveClientResponse extends CoreResponse {

    private boolean clientRemoved;

    public RemoveClientResponse(List<CoreError> errors) {
        super(errors);
    }

    public RemoveClientResponse(boolean clientRemoved) {
        this.clientRemoved = clientRemoved;
    }

    public boolean isClientRemoved() {
        return clientRemoved;
    }
}
