package lv.javaguru.java2.tasksScheduler.requests.ordering_paging;

public class Paging {
    private int pageNumber;
    private int pageSize;

    public Paging(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }
}
