package lv.javaguru.java2.tasksScheduler.requests;

import java.time.LocalDateTime;

public class SearchTasksRequest {
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    public SearchTasksRequest(String description, LocalDateTime start, LocalDateTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
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
}
