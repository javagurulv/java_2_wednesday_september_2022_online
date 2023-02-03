package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import lv.javaguru.java2.tasksScheduler.core.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.GetCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetCurrentUserService;
import lv.javaguru.java2.tasksScheduler.utils.WebUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AmendTaskController {

    @Autowired
    private GetCurrentUserService getCurrentUserService;

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

}
