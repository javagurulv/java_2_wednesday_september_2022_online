package lv.javaguru.java2.tasksScheduler.core.requests;

import java.time.LocalDateTime;

public class GetOutstandingTasksRequest {
    LocalDateTime endDate;

    public GetOutstandingTasksRequest(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
