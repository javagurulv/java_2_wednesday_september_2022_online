package lv.javaguru.java2.tasksScheduler.responses;

import lv.javaguru.java2.tasksScheduler.domain.Task;

import java.util.List;

public class AmendTaskResponse extends CoreResponse {

    private Task task;

    public AmendTaskResponse(List<CoreError> errors) {
        super(errors);
    }

    public AmendTaskResponse(Task task) {
        this.task = task;
    }

    public Task getAmendedTask() {
        return task;
    }
}
