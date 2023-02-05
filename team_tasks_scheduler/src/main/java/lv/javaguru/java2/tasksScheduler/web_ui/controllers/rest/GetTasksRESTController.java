package lv.javaguru.java2.tasksScheduler.web_ui.controllers.rest;

import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.requests.SearchTasksRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.responses.SearchTasksResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.SearchTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/searchTasks")
public class GetTasksRESTController {

    @Autowired
    private SearchTasksService searchTasksService;

    @PostMapping(value="/{searchPhrase}", consumes = "application/json", produces = "application/json")
    ResponseEntity<List<Map<String,String>>> getTasks(@RequestBody Map<String,String> viewParam,
                                                             @PathVariable String searchPhrase) {
        Ordering ordering = new Ordering(viewParam.get("orderBy").toLowerCase().replace("-", " "),
                                        viewParam.get("ordering").toLowerCase().replace("-", " "));
        Paging paging = new Paging(Integer.valueOf(viewParam.get("pageNumber")),
                                                 Integer.valueOf(viewParam.get("pageSize")));

        SearchTasksRequest request = new SearchTasksRequest(searchPhrase, ordering, paging);
        SearchTasksResponse response = searchTasksService.execute(request);
        if (response.hasErrors()) {
            List<Map<String,String>> errorMapList = createErrorMapList(response);
            return new ResponseEntity<>(errorMapList, HttpStatus.BAD_REQUEST);
        }

        List<Map<String,String>> userNamesMapList = createTaskMapList(response);

        return new ResponseEntity<>(userNamesMapList, HttpStatus.OK);
    }

    private List<Map<String,String>> createErrorMapList(SearchTasksResponse response) {
        List<Map<String,String>> errorMapList = new ArrayList<>();
        List<CoreError> errors = response.getErrors();
        for (CoreError error : errors) {
            Map<String,String> errorMap = new HashMap<>();
            errorMap.put("error", error.getField());
            errorMap.put("msg", error.getMessage());
            errorMapList.add(errorMap);
        }
        return errorMapList;
    }

    private List<Map<String,String>> createTaskMapList(SearchTasksResponse response) {
        List<Map<String,String>> tasksMapList = new ArrayList<>();
        for (Task task : response.getTasks()) {
            Map<String,String> taskMap = new HashMap<>();
            taskMap.put("description", task.getDescription());
            taskMap.put("regularity", Integer.toString(task.getRegularity()));
            taskMap.put("dueDate", task.getDueDate().toString());
            taskMap.put("endDate", task.getEndDate().toString());
            tasksMapList.add(taskMap);
        }
        return tasksMapList;
    }
}

