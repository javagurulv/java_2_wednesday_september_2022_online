package lv.javaguru.java2.tasksScheduler.requests.ordering_paging;

public class Paging {
    private Integer pageNumber;
    private Integer pageSize;

    public Paging(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
