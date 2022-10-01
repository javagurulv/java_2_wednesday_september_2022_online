package eBooking.response;

public class RemoveClientResponse {

    private boolean clientRemoved;

    public RemoveClientResponse(boolean clientRemoved) {
        this.clientRemoved = clientRemoved;
    }

    public boolean isClientRemoved() {
        return clientRemoved;
    }
}
