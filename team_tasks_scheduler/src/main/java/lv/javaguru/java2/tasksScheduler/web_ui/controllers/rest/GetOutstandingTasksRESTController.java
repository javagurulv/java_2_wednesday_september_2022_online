package lv.javaguru.java2.tasksScheduler.web_ui.controllers.rest;


import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.requests.GetOutstandingTasksRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.responses.GetOutstandingTasksResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.GetOutstandingTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/getOutstandingTasks")
public class GetOutstandingTasksRESTController {

    @Autowired
    private GetOutstandingTasksService getOutstandingTasksService;

    @GetMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<List<Map<String,String>>> getOutstandingTasks() {
        GetOutstandingTasksRequest request = new GetOutstandingTasksRequest(LocalDateTime.MAX);
        GetOutstandingTasksResponse response = getOutstandingTasksService.execute(request);
        if (response.hasErrors()) {
            List<Map<String,String>> errorMapList = createErrorMapList(response);
            return new ResponseEntity<>(errorMapList, HttpStatus.BAD_REQUEST);
        }

        List<Map<String,String>> userNamesMapList = createTaskMapList(response);

        return new ResponseEntity<>(userNamesMapList, HttpStatus.OK);
    }


    private List<Map<String,String>> createErrorMapList(GetOutstandingTasksResponse response) {
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

    private List<Map<String,String>> createTaskMapList(GetOutstandingTasksResponse response) {
        List<Map<String,String>> tasksMapList = new ArrayList<>();
        for (Task task : response.getTasks()) {
            Map<String,String> taskMap = new HashMap<>();
            taskMap.put("id", task.getId().toString());
            taskMap.put("description", task.getDescription());
            taskMap.put("regularity", Integer.toString(task.getRegularity()));
            taskMap.put("dueDate", task.getDueDate().toString());
            taskMap.put("endDate", task.getEndDate().toString());
            tasksMapList.add(taskMap);
        }
        return tasksMapList;
    }
}
