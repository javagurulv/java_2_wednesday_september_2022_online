package lv.javaguru.java2.tasksScheduler.responses;

import java.util.List;

public class DeleteCurrentUserResponse extends CoreResponse{

    String deletedUserName;
    public DeleteCurrentUserResponse(String user) {
        this.deletedUserName = user;
    }

    public DeleteCurrentUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public String getDeletedUserName() {
        return deletedUserName;
    }
}
