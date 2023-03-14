package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import lv.javaguru.java2.tasksScheduler.core.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetOutstandingTasksRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.GetCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.GetOutstandingTasksResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.GetOutstandingTasksService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetCurrentUserService;
import lv.javaguru.java2.tasksScheduler.utils.WebUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;


@Controller
public class ShowTasksForTodayController {

    @Autowired
    private GetCurrentUserService getCurrentUserService;
    @Autowired
    private GetOutstandingTasksService getOutstandingTasksService;

    @GetMapping(value = "/showTasksForToday")
    public String showUserAmendmentPage(@RequestParam Map<String,String> allParams,
                                                                ModelMap modelMap,
                                                                HttpSession session) {
        GetCurrentUserRequest getNameRequest = new GetCurrentUserRequest(session.getId(),true);
        GetCurrentUserResponse getNameResponse = getCurrentUserService.execute(getNameRequest);
        WebUI.addToPageUserGreeting(modelMap, getNameResponse);

        String btnValue = allParams.get("buttonShowTasks");
        if (btnValue != null) {
            if (btnValue.equals("Show")) {
                LocalDateTime date = LocalDateTime.now().plusDays(1).with(LocalTime.MIN);
                GetOutstandingTasksRequest request = new GetOutstandingTasksRequest(date, session.getId());
                GetOutstandingTasksResponse response = getOutstandingTasksService.execute(request);
                if (response.hasErrors()) {
                    modelMap.addAttribute("errors", response.getErrors());
                } else {
                    modelMap.addAttribute("tasks", response.getTasks());
                }
            }
        }

        return "showTodayTasks";
    }

}
