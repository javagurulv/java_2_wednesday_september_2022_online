package lv.javaguru.java2.tasksScheduler.core.responses;

import java.util.List;

public class DeleteUserResponse extends CoreResponse{

    private String deletedUserName;

    public DeleteUserResponse(String user) {
        this.deletedUserName = user;
    }

    public DeleteUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public String getDeletedUserName() {
        return deletedUserName;
    }
}
