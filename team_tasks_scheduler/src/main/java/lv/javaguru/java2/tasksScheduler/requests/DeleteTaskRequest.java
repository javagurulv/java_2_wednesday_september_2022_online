package lv.javaguru.java2.tasksScheduler.requests;


public class DeleteTaskRequest {
    Long taskId;

    public DeleteTaskRequest(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskId() {
        return taskId;
    }
}
