package lv.javaguru.java2.tasksScheduler.core.responses;

import lv.javaguru.java2.tasksScheduler.core.domain.User;

import java.util.List;

public class AmendCurrentUserResponse extends CoreResponse {

    User user;

    public AmendCurrentUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public AmendCurrentUserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
