package lv.javaguru.java2.eBooking.core.requests.client_request;

public class ClientRemoveRequest {
    private Long removeClientId;

    public ClientRemoveRequest(Long removeClientId) {
        this.removeClientId = removeClientId;
    }

    public Long getRemoveClientId() {
        return removeClientId;
    }
}
