package lv.javaguru.java2.tasksScheduler.requests;

import java.time.LocalDateTime;

public class AddTaskRequest {

    private String description;
    private int regularity;
    private LocalDateTime dueDate;
    private LocalDateTime  endDate;

    public AddTaskRequest(String description, int regularity, LocalDateTime dueDate,
                                    LocalDateTime endDate) {
        this.description = description;
        this.regularity = regularity;
        this.dueDate = dueDate;
        this.endDate = endDate;
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

}
