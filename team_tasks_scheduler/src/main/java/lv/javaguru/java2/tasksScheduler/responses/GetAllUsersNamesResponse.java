package lv.javaguru.java2.tasksScheduler.responses;

import lv.javaguru.java2.tasksScheduler.domain.User;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsersNamesResponse extends CoreResponse {
    List<String> userNames;

    public GetAllUsersNamesResponse(List<String> userNames, List<CoreError> errors) {
        super(errors);
        this.userNames = userNames;
    }

    public List<String> getUserNames() {
        return userNames;
    }
}
