package lv.javaguru.java2.tasksScheduler.core.requests;

import java.time.LocalDateTime;

public class GetOutstandingTasksRequest {
    private LocalDateTime endDate;
    private String sessionId;

    public GetOutstandingTasksRequest(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public GetOutstandingTasksRequest(LocalDateTime endDate, String sessionId) {
        this(endDate);
        this.sessionId = sessionId;
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
