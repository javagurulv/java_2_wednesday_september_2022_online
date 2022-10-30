package lv.javaguru.java2.tasksScheduler.requests;

import lv.javaguru.java2.tasksScheduler.domain.Task;

public class AmendTaskRequest {

    private Task task;

    public AmendTaskRequest(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
