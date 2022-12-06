package lv.javaguru.java2.tasksScheduler.core.responses;

import lv.javaguru.java2.tasksScheduler.core.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class GetUsersResponse extends CoreResponse {
    List<User> users;

    public GetUsersResponse(List<User> users, List<CoreError> errors) {
        super(errors);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<String> getUsersNames() {
        return users.stream().
                map(User::getUsername).collect(Collectors.toList());
    }
}
