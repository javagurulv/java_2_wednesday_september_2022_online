package lv.javaguru.java2.tasksScheduler.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private Long id;
    private String description;
    private int regularity;
    private LocalDateTime  dueDate;
    private LocalDateTime  endDate;
    private Long userId;

    public Task(String description, int regularity, LocalDateTime  dueDate, LocalDateTime  endDate, Long userId) {
        this.description = description;
        this.regularity = regularity;
        this.dueDate = dueDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public Task(Task task) {
        this.id = task.getId();
        this.description = task.getDescription();
        this.regularity = task.getRegularity();
        this.dueDate = task.getDueDate();
        this.endDate = task.getEndDate();
        this.userId = task.getUserId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRegularity() {
        return regularity;
    }

    public void setRegularity(int regularity) {
        this.regularity = regularity;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime  dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime  getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime  endDate) {
        this.endDate = endDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return regularity == task.regularity &&
                Objects.equals(id, task.id) &&
                Objects.equals(description, task.description) &&
                Objects.equals(dueDate, task.dueDate) &&
                Objects.equals(endDate, task.endDate) &&
                Objects.equals(userId, task.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, regularity, dueDate, endDate, userId);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", regularity=" + regularity +
                ", dueDate=" + dueDate +
                ", endDate=" + endDate +
                ", userId=" + userId +
                '}';
    }
}
