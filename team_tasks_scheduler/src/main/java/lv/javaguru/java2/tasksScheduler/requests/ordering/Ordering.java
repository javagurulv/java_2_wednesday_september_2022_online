package lv.javaguru.java2.tasksScheduler.requests.ordering;

public class Ordering {
    private String orderBy;
    private String orderDirection;

    public Ordering(String orderBy, String orderDirection) {
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
    }
}
