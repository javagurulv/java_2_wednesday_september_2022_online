package lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging;

public class Ordering {
    private String orderBy;
    private String orderDirection;

    public Ordering() {
    }

    public Ordering(String orderBy, String orderDirection) {
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
    }
    public String getOrderBy() {
        return orderBy;
    }
    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }
}
