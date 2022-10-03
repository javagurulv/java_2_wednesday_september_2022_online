package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.SearchTasksRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.SearchTasksResponse;
import lv.javaguru.java2.tasksScheduler.services.validators.SearchTasksValidator;

import java.util.List;

public class SearchTasksService {

    private TasksRepository tasksRepository;
    private SearchTasksValidator validator;

    public SearchTasksService(TasksRepository tasksRepository, SearchTasksValidator validator) {
        this.tasksRepository = tasksRepository;
        this.validator = validator;
    }

    SearchTasksResponse execute(SearchTasksRequest request) {
        List<CoreError>errors = validator.validate(request);

        List<Task> taskList = tasksRepository.searchTaskByDescription(request.getDescription());

        return new SearchTasksResponse(taskList, errors);
    }
}
