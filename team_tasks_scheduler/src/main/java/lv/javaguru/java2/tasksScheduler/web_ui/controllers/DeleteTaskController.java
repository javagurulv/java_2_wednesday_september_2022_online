package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.requests.DeleteTaskRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetOutstandingTasksRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.DeleteTaskResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.GetCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.GetOutstandingTasksResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.DeleteTaskService;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.GetOutstandingTasksService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetCurrentUserService;
import lv.javaguru.java2.tasksScheduler.utils.WebUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@Controller
public class DeleteTaskController {
    @Autowired
    private GetCurrentUserService getCurrentUserService;
    @Autowired
    private GetOutstandingTasksService getOutstandingTasksService;
    @Autowired
    private DeleteTaskService deleteTaskService;

    @GetMapping(value = "/deleteTask")
    public String showTaskListToDelete(@RequestParam Map<String,String> allParams,
                                                                ModelMap modelMap,
                                                                HttpSession session) {
        GetCurrentUserRequest getNameRequest = new GetCurrentUserRequest(session.getId(),true);
        GetCurrentUserResponse getNameResponse = getCurrentUserService.execute(getNameRequest);
        if (getNameResponse.hasErrors()) {
            modelMap.addAttribute("errors", getNameResponse.getErrors());
        } else {
            modelMap.addAttribute("greeting",
                    WebUI.getGreeting(getNameResponse.getUser().getUsername()));
            modelMap.addAttribute("request", getNameResponse.getUser());
        }

        String btnValue = allParams.get("buttonShowTasks");
        if (btnValue != null) {
            if (btnValue.equals("Show")) {
                GetOutstandingTasksRequest request = new GetOutstandingTasksRequest(LocalDateTime.MAX, session.getId());
                GetOutstandingTasksResponse response = getOutstandingTasksService.execute(request);
                if (response.hasErrors()) {
                    modelMap.addAttribute("errors", response.getErrors());
                } else {
                    modelMap.addAttribute("tasks", response.getTasks());
                }
            }
        }

        return "deleteTask";
    }

    @PostMapping(value = "/deleteTask")
    public String deleteTask(@RequestParam Map<String,String> allParams,
                                                            ModelMap modelMap,
                                                            HttpSession session) {
        GetCurrentUserRequest getNameRequest = new GetCurrentUserRequest(session.getId(),true);
        GetCurrentUserResponse getNameResponse = getCurrentUserService.execute(getNameRequest);
        WebUI.addToPageUserGreeting(modelMap, getNameResponse);

        Task task = createTaskFromRequestParams(allParams, getNameResponse.getUser().getId());
        Long taskId = task.getId();
        DeleteTaskRequest request = new DeleteTaskRequest(taskId);
        DeleteTaskResponse response = deleteTaskService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("succeed", true);
        }

        return "deleteTask";
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




