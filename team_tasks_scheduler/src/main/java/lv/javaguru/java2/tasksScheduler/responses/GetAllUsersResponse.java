package lv.javaguru.java2.tasksScheduler.responses;

import lv.javaguru.java2.tasksScheduler.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllUsersResponse extends CoreResponse {
    List<User> users;

    public GetAllUsersResponse(List<User> users, List<CoreError> errors) {
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
