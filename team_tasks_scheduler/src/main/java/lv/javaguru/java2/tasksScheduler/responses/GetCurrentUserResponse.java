package lv.javaguru.java2.tasksScheduler.responses;

import lv.javaguru.java2.tasksScheduler.domain.User;

import java.util.List;

public class GetCurrentUserResponse extends CoreResponse{
    User user;

    public GetCurrentUserResponse(List<CoreError> errors) {
        super(errors);
    }
    public GetCurrentUserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
