package lv.javaguru.java2.tasksScheduler.requests.ordering_paging;

public class Paging {
    private Integer pageNumber;
    private Integer pageSize;

    public Paging(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
