package lv.javaguru.java2.eBooking.core.request;

public class RemoveClientRequest {
    private Long removeClientId;

    public RemoveClientRequest(Long removeClientId) {
        this.removeClientId = removeClientId;
    }

    public Long getRemoveClientId() {
        return removeClientId;
    }
}
