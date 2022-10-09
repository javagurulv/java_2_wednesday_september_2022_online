package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.SearchTasksRequest;
import lv.javaguru.java2.tasksScheduler.requests.ordering.Ordering;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.SearchTasksResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.services.validators.SearchTasksValidator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchTasksService {

    private TasksRepository tasksRepository;
    private SearchTasksValidator validator;
    private SessionService sessionService;

    public SearchTasksService(TasksRepository tasksRepository, SearchTasksValidator validator,
                              SessionService sessionService) {
        this.tasksRepository = tasksRepository;
        this.validator = validator;
        this.sessionService = sessionService;
    }

    public SearchTasksResponse execute(SearchTasksRequest request) {
        List<CoreError>errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchTasksResponse(null, errors);
        }

        Ordering ordering = request.getOrdering();

        List<Task> taskList = tasksRepository.searchTasks(request.getSearchPhrase(),
                sessionService.getCurrentUserId());
        taskList = order(taskList, request.getOrdering());

        return new SearchTasksResponse(taskList, errors);
    }

    private List<Task> order(List<Task> tasks, Ordering ordering) {
        if (ordering != null) {
            Comparator<Task> comparator = ordering.getOrderBy().equals("description")
                    ? Comparator.comparing(Task::getDescription)
                    : Comparator.comparing(Task::getEndDate);
            if (ordering.getOrderDirection().equals("descending")) {
                comparator = comparator.reversed();
            }
            return tasks.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return tasks;
        }
    }
}
