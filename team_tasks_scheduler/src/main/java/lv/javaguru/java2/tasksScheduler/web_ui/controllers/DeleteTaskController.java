package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import lv.javaguru.java2.tasksScheduler.core.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetOutstandingTasksRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.GetCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.GetOutstandingTasksResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.GetOutstandingTasksService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetCurrentUserService;
import lv.javaguru.java2.tasksScheduler.utils.WebUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Map;

public class DeleteTaskController {
    @Autowired
    private GetCurrentUserService getCurrentUserService;
    @Autowired
    private GetOutstandingTasksService getOutstandingTasksService;

    @GetMapping(value = "/deleteTask")
    public String showTaskListToDelete(@RequestParam Map<String,String> allParams,
                                        ModelMap modelMap) {
        GetCurrentUserRequest getNameRequest = new GetCurrentUserRequest(true);
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
                GetOutstandingTasksRequest request = new GetOutstandingTasksRequest(LocalDateTime.MAX);
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


}


