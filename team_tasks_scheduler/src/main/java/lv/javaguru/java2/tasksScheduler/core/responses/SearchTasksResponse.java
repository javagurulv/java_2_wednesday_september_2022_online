package lv.javaguru.java2.tasksScheduler.core.responses;

import lv.javaguru.java2.tasksScheduler.core.domain.Task;

import java.util.List;

public class SearchTasksResponse extends CoreResponse {

    List<Task> tasks;

    public SearchTasksResponse(List<Task> tasks, List<CoreError> errors) {
        super(errors);
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
