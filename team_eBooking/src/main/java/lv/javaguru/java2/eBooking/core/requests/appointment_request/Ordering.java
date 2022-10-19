package lv.javaguru.java2.eBooking.core.requests.appointment_request;

public class Ordering {
    private String OrderBy;

    public Ordering(String orderBy) {
        OrderBy = orderBy;
    }

    public String getOrderBy() {
        return OrderBy;
    }
}
