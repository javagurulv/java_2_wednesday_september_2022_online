package lv.javaguru.java2.tasksScheduler.web_ui.controllers;


import lv.javaguru.java2.tasksScheduler.core.requests.UserRegistrationRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.UserRegistrationResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @GetMapping(value = "/userRegistration")
    public String showUserRegistrationPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new UserRegistrationRequest());
        return "userRegistration";
    }

    @PostMapping("/userRegistration")
    public String processRegistrationRequest(@ModelAttribute(value = "request") UserRegistrationRequest request, ModelMap modelMap) {
        UserRegistrationResponse response = userRegistrationService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("succeed", response.getUser());
        }
        return "userRegistration";
    }

}
