package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import lv.javaguru.java2.tasksScheduler.core.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.GetCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetCurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserMenuController {

    @Autowired private GetCurrentUserService getCurrentUserService;

    @GetMapping(value = "/userMenu")
    public String userMenu(ModelMap modelMap) {
        GetCurrentUserRequest request = new GetCurrentUserRequest();
        GetCurrentUserResponse response = getCurrentUserService.execute(request);
        modelMap.addAttribute("current_user", response.getUser().getUsername());
        return "userMenu";
    }
}
