package lv.javaguru.java2.tasksScheduler.responses;

import lv.javaguru.java2.tasksScheduler.domain.User;

import java.util.List;

public class LoginResponse extends CoreResponse {
    User user;
    public LoginResponse(List<CoreError> errors) {
        super(errors);
    }
    public LoginResponse(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
}
