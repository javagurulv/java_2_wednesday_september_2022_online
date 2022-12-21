package lv.javaguru.java2.tasksScheduler.web_ui.controllers;


import lv.javaguru.java2.tasksScheduler.core.requests.AmendCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.UserRegistrationRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AmendCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.GetCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.UserRegistrationResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.AmendCurrentUserService;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.UserRegistrationService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetCurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserAmendmentController {

    @Autowired
    private AmendCurrentUserService amendCurrentUserService;
    @Autowired private GetCurrentUserService getCurrentUserService;

    @GetMapping(value = "/userAmendment")
    public String showUserAmendmentPage(ModelMap modelMap) {
        GetCurrentUserRequest request = new GetCurrentUserRequest(true);
        GetCurrentUserResponse response = getCurrentUserService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("current_user", response.getUser().getUsername());
            modelMap.addAttribute("request", response.getUser());
        }
        return "userAmendment";
    }

    @PostMapping("/userAmendment")
    public String processAmendmentRequest(@ModelAttribute(value = "request") AmendCurrentUserRequest request, ModelMap modelMap) {
        AmendCurrentUserResponse response = amendCurrentUserService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("current_user", response.getUser().getUsername());
            modelMap.addAttribute("succeed", response.getUser());
        }
        return "userAmendment";
    }

}
