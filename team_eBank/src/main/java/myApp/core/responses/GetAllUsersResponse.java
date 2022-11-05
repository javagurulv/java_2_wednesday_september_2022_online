package myApp.core.responses;

import myApp.core.domain.User;

import java.util.List;

public class GetAllUsersResponse extends CoreResponse {

    private List<User> users;

    public GetAllUsersResponse(List<CoreError> errors, List<User> users) {
        super(errors);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
