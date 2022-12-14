package lv.javaguru.java2.tasksScheduler.core.responses;

import lv.javaguru.java2.tasksScheduler.core.domain.User;

import java.util.List;

public class UserRegistrationResponse extends CoreResponse {

    private User user;

    public UserRegistrationResponse(List<CoreError> errors) {
        super(errors);
    }
    public UserRegistrationResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
