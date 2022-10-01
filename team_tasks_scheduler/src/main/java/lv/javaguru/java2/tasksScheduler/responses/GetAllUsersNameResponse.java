package lv.javaguru.java2.tasksScheduler.responses;

import lv.javaguru.java2.tasksScheduler.domain.User;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsersNameResponse extends CoreResponse {
    List<User> users;
    public GetAllUsersNameResponse(List<User> users, int a) {
        this.users = users;
        //TODO get rid of a :)
    }

    public GetAllUsersNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public List<User> getUsers() {
        return users;
    }
}
