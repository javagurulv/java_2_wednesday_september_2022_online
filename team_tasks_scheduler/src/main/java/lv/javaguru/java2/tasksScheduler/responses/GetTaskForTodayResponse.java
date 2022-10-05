package lv.javaguru.java2.tasksScheduler.responses;

import lv.javaguru.java2.tasksScheduler.domain.Task;

import java.util.List;

public class GetTaskForTodayResponse extends CoreResponse{
    List<Task> tasks;

    public GetTaskForTodayResponse(List<Task> tasks, int a) { //TODO solve for a
        this.tasks = tasks;
    }

    public GetTaskForTodayResponse(List<CoreError> errors) {
        super(errors);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
