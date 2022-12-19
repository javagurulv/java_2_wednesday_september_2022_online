package lv.javaguru.java2.tasksScheduler.core.services.menu_services;

import lv.javaguru.java2.tasksScheduler.core.database.TasksRepository;


import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.requests.SearchTasksRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.responses.SearchTasksResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.core.services.validators.SearchTasksValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class SearchTasksService {

    @Autowired private TasksRepository tasksRepository;
    @Autowired private SearchTasksValidator validator;
    @Autowired private SessionService sessionService;

    public SearchTasksResponse execute(SearchTasksRequest request) {
        List<CoreError>errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchTasksResponse(null, errors);
        }

        List<Task> taskList = tasksRepository.searchTasks(request.getSearchPhrase(),
                sessionService.getCurrentUserId());

        taskList = order(taskList, request.getOrdering());
        taskList = paging(taskList,request.getPaging());

        return new SearchTasksResponse(taskList, errors);
    }

    private List<Task> order(List<Task> tasks, Ordering ordering) {
        if (ordering != null) {
            //order by ID by default
            Comparator<Task> comparator = Comparator.comparing(Task::getId);

            if (ordering.getOrderBy().equals("description")) {
                comparator = Comparator.comparing(Task::getDescription);
            }
            else if (ordering.getOrderBy().equals("due date")) {
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
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return tasks.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return tasks;
        }
    }
}
