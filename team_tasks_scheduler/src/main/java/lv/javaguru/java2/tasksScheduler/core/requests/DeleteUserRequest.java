package lv.javaguru.java2.tasksScheduler.core.requests;

public class DeleteUserRequest {

    private Long userId;
    public DeleteUserRequest() {
    }

    public DeleteUserRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
