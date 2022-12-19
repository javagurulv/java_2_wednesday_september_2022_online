package lv.javaguru.java2.tasksScheduler.core.requests;

import lv.javaguru.java2.tasksScheduler.core.domain.Task;

public class AmendTaskRequest {

    private Task task;

    public AmendTaskRequest(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
