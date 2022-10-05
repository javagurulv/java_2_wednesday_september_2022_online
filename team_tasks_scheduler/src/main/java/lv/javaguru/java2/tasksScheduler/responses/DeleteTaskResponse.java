package lv.javaguru.java2.tasksScheduler.responses;

import java.util.List;

public class DeleteTaskResponse extends CoreResponse {
    public DeleteTaskResponse() {
    }

    public DeleteTaskResponse(List<CoreError> errors) {
        super(errors);
    }
}
