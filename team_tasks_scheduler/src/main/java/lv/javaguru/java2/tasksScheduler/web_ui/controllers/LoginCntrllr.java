package lv.javaguru.java2.tasksScheduler.web_ui.controllers;


import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import lv.javaguru.java2.tasksScheduler.requests.GetUsersRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetUsersResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.GetOutstandingTasksService;
import lv.javaguru.java2.tasksScheduler.services.menu_services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginCntrllr {

    @Autowired
    private LoginService loginService;
    @Autowired
    private GetOutstandingTasksService getOutstandingTasksService;

    @GetMapping(value = "/userLogin")
    public String showUsernamesRegisteredOnSystem(ModelMap modelMap) {

        return "/userLogin";
    }

}
