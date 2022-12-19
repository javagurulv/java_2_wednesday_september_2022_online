package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import lv.javaguru.java2.tasksScheduler.core.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.LogoutRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.GetCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.LoginResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.LogoutResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.LogoutService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetCurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LogoutController {

    @Autowired private LogoutService logoutService;

    @GetMapping(value = "/userLogout")
    public String logout() {
        LogoutRequest request = new LogoutRequest();
        LogoutResponse response = logoutService.execute(request);
        return "redirect:userLogin";
    }
}
