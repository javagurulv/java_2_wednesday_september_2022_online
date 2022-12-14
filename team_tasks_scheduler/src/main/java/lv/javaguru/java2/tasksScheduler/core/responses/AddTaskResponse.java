package lv.javaguru.java2.tasksScheduler.core.responses;

import lv.javaguru.java2.tasksScheduler.core.domain.Task;

import java.util.List;

public class AddTaskResponse extends CoreResponse{

    private Task task;

    public AddTaskResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddTaskResponse(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
