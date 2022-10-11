package lv.javaguru.java2.tasksScheduler.requests;

import lv.javaguru.java2.tasksScheduler.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.requests.ordering_paging.Paging;

import java.time.LocalDateTime;

public class SearchTasksRequest {
    private Ordering ordering;
    private Paging paging;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    private String searchPhrase;
    public SearchTasksRequest(String description, LocalDateTime start, LocalDateTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public SearchTasksRequest(String searchPhrase, Ordering ordering) {
        this.searchPhrase = searchPhrase;
        this.ordering = ordering;
    }

    public SearchTasksRequest(String searchPhrase, Ordering ordering, Paging paging) {
        this.searchPhrase = searchPhrase;
        this.ordering = ordering;
        this.paging = paging;
    }
    public String getDescription() { return description; }
    public LocalDateTime getEndDate() {
        return end;
    }
    public LocalDateTime getStartDate() {
        return start;
    }
    public String getSearchPhrase() {
        return searchPhrase;
    }

    public Ordering getOrdering() {
        return ordering;
    }
    public Paging getPaging() {
        return paging;
    }
}
