package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.SearchTasksRequest;
import lv.javaguru.java2.tasksScheduler.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.SearchTasksResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.services.validators.SearchTasksValidator;

import java.util.ArrayList;
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
        taskList = paging(taskList,request.getPaging());

        return new SearchTasksResponse(taskList, errors);
    }

    private List<Task> order(List<Task> tasks, Ordering ordering) {
        if (ordering != null) {
            //order by description by default
            Comparator<Task> comparator = Comparator.comparing(Task::getDescription);
            if (ordering.getOrderBy().equals("due date")) {
                comparator = Comparator.comparing(Task::getDueDate);
            }
            else if (ordering.getOrderBy().equals("end date")) {
                comparator = Comparator.comparing(Task::getEndDate);
            }
            if (ordering.getOrderDirection().equals("descending")) {
                comparator = comparator.reversed();
            }
            return tasks.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return tasks;
        }
    }

    private List<Task> paging(List<Task> tasks, Paging paging) {
        if (paging != null) {
            int pageNumber = paging.getPageNumber();
            int pageSize = paging.getPageSize();
            int start = (pageNumber-1)*pageSize;
            List<Task> newTaskList = new ArrayList<>();

            int i = start;
            do {
                newTaskList.add(tasks.get(i));
                i++;
            }
            while (i < pageSize);

            return newTaskList;
        } else {
            return tasks;
        }
    }
}
