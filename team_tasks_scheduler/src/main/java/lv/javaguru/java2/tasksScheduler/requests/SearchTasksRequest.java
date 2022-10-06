package lv.javaguru.java2.tasksScheduler.requests;

import lv.javaguru.java2.tasksScheduler.responses.SearchTasksResponse;

import java.time.LocalDateTime;

public class SearchTasksRequest {
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    private String searchPhrase;
    public SearchTasksRequest(String description, LocalDateTime start, LocalDateTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public SearchTasksRequest(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getDescription() {
        return description;
    }
    public LocalDateTime getEndDate() {
        return end;
    }
    public LocalDateTime getStartDate() {
        return start;
    }
    public String getSearchPhrase() {
        return searchPhrase;
    }
}
