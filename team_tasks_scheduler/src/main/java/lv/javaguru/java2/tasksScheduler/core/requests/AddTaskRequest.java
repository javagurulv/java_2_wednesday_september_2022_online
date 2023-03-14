package lv.javaguru.java2.tasksScheduler.core.requests;

import java.time.LocalDateTime;

public class AddTaskRequest {

    private String description;
    private int regularity;
    private LocalDateTime dueDate;
    private LocalDateTime  endDate;

    private String sessionId;

    public AddTaskRequest(String description, int regularity, LocalDateTime dueDate,
                                    LocalDateTime endDate) {
        this.description = description;
        this.regularity = regularity;
        this.dueDate = dueDate;
        this.endDate = endDate;
    }

    public AddTaskRequest(String description, int regularity, LocalDateTime dueDate,
                          LocalDateTime endDate, String sessionId) {
        this(description, regularity, dueDate, endDate);
        this.sessionId = sessionId;
    }

    public String getDescription() {
        return description;
    }

    public int getRegularity() {
        return regularity;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
