package lv.javaguru.java2.tasksScheduler.responses;

import lv.javaguru.java2.tasksScheduler.domain.Task;

import java.util.List;

public class AmendTaskResponse extends CoreResponse {

    Task amendedTask;
    public AmendTaskResponse(List<CoreError> errors) {
        super(errors);
    }

    public AmendTaskResponse(Task amendedTask) {
        this.amendedTask = amendedTask;
    }

    public Task getAmendedTask() {
        return amendedTask;
    }
}
