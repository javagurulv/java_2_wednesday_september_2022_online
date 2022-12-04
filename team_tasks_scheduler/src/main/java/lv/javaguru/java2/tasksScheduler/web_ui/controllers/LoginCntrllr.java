package lv.javaguru.java2.tasksScheduler.web_ui.controllers;


import lv.javaguru.java2.tasksScheduler.core.services.menu_services.GetOutstandingTasksService;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.LoginService;
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
