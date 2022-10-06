package lv.javaguru.java2.tasksScheduler.responses;

import lv.javaguru.java2.tasksScheduler.domain.User;

import java.util.List;

public class UserRegistrationResponse extends CoreResponse{

    private User user;

    public UserRegistrationResponse(User user, List<CoreError> errors) {
        super(errors);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
