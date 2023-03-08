package lv.javaguru.java2.tasksScheduler.web_ui.controllers.rest;


import lv.javaguru.java2.tasksScheduler.core.requests.AddTaskRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AddTaskResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.responses.UserRegistrationResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.AddTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/api/addTask")
public class AddTaskRESTController {

    @Autowired
    private AddTaskService addTaskService;


    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<List<Map<String,String>>> processAddTaskRequest(@RequestBody Map<String,String> newTaskInfo) {

        LocalDateTime dueDate = getDateFromInputData(newTaskInfo, "dueDate");
        LocalDateTime endDate = getDateFromInputData(newTaskInfo, "endDate");
        int regularity = parseInt(newTaskInfo.get("regularity"));

        AddTaskRequest request = new AddTaskRequest(newTaskInfo.get("taskDescription"), regularity,
                                                                                dueDate, endDate);
        AddTaskResponse response = addTaskService.execute(request);

        if (response.hasErrors()) {
            List<Map<String,String>> errorMapList = createErrorMapList(response);
            return new ResponseEntity<>(errorMapList, HttpStatus.BAD_REQUEST);
        }

        Map<String,String> responseMap = new HashMap<>();
        responseMap.put("message", "Task added successfully");
        List<Map<String,String>> responseMapList = new ArrayList<>();
        responseMapList.add(responseMap);

        return new ResponseEntity<>(responseMapList, HttpStatus.OK);

    }





    private List<Map<String,String>> createErrorMapList(AddTaskResponse response) {
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
    LocalDateTime getDateFromInputData(Map<String,String> inputData, String identifier) {
        LocalDateTime dateTime = LocalDateTime.now();

        if (identifier.equals("dueDate")) {
            try {
                LocalDate date = LocalDate.parse(inputData.get("dueDate"));
                LocalTime time = LocalTime.parse(inputData.get("dueTime"));
                dateTime = LocalDateTime.of(date, time);
            } catch (RuntimeException e) {
                //set unacceptable date to get error from validator
                dateTime = LocalDateTime.of(1973, 01, 01, 1, 1);
            }
        }
        else if (identifier.equals("endDate")) {
            try {
                LocalDate date = LocalDate.parse(inputData.get("endDate"));
                LocalTime time = LocalTime.parse(inputData.get("endTime"));
                dateTime = LocalDateTime.of(date, time);
            } catch (RuntimeException e) {
                //set unacceptable date to get error from validator
                dateTime = LocalDateTime.of(1973, 01, 01, 1, 1);
            }
        }
        return dateTime;
    }

}


