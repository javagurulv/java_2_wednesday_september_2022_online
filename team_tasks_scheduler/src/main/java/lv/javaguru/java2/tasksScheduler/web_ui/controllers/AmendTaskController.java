package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.requests.AmendTaskRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AmendTaskResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.GetCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.AmendTaskService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetCurrentUserService;
import lv.javaguru.java2.tasksScheduler.utils.WebUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;


@Controller
public class AmendTaskController {

    @Autowired
    private GetCurrentUserService getCurrentUserService;
    @Autowired
    private AmendTaskService amendTaskService;

    @GetMapping(value = "/taskAmendment")
    public String showUserAmendmentPage(ModelMap modelMap) {
        GetCurrentUserRequest request = new GetCurrentUserRequest(true);
        GetCurrentUserResponse response = getCurrentUserService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("greeting", WebUI.getGreeting(response.getUser().getUsername()));
            modelMap.addAttribute("request", response.getUser());
        }
        return "taskAmendment";
    }

    @PostMapping(value = "/taskAmendment")
    public String amendTasks(@RequestParam Map<String,String> allParams, ModelMap modelMap) {
        GetCurrentUserRequest request = new GetCurrentUserRequest(true);
        GetCurrentUserResponse response = getCurrentUserService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("greeting", WebUI.getGreeting(response.getUser().getUsername()));
            modelMap.addAttribute("request", response.getUser());
        }

        Task task = createTaskFromRequestParams(allParams, response.getUser().getId());

        AmendTaskRequest amendTaskRequest = new AmendTaskRequest(task);
        AmendTaskResponse amendTaskResponse = amendTaskService.execute(amendTaskRequest);

        if (amendTaskResponse.hasErrors()) {
            modelMap.addAttribute("errors", amendTaskResponse.getErrors());
        } else {
            modelMap.addAttribute("succeed", "Task amended");
        }

        return "taskAmendment";
    }

    private Task createTaskFromRequestParams(Map<String,String> allParams, Long userId) {
        LocalDateTime dueDate = createDateFromRequestParams(allParams, "dueTime");
        LocalDateTime endDate = createDateFromRequestParams(allParams, "endTime");
        int regularity;
        Long taskId;

        try {
            regularity = parseInt(allParams.get("regularity"));
        } catch (Exception e) {
            regularity = -1;
        }
        try {
            taskId = parseLong(allParams.get("taskId"));
        } catch (Exception e) {
            taskId = -1L;
        }
        Task task = new Task(allParams.get("taskDescription"), regularity,
                                                    dueDate, endDate, userId);
        task.setId(taskId);

        return task;
    }

    private LocalDateTime createDateFromRequestParams(Map<String,String> allParams, String type) {
        LocalDateTime dateTime = null;
        
        if (type.equals("dueTime")) {
            String timeStr =  allParams.get("dueTime");
            String dateStr =  allParams.get("dueDate");
            LocalTime time;
            LocalDate date;
            try {
                time = LocalTime.parse(timeStr);
                date = LocalDate.parse(dateStr);
            } catch (Exception e) {
                return null;
            }
            dateTime = LocalDateTime.of(date, time);
        } else if (type.equals("endTime")) {
            String timeStr =  allParams.get("endTime");
            String dateStr =  allParams.get("endDate");
            LocalTime time;
            LocalDate date;
            try {
                time = LocalTime.parse(timeStr);
                date = LocalDate.parse(dateStr);
            } catch (Exception e) {
                return null;
            }
            dateTime = LocalDateTime.of(date, time);
        }

        return dateTime;
    }
}
