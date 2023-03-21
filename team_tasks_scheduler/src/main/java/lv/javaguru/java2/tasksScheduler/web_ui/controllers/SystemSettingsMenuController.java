package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import lv.javaguru.java2.tasksScheduler.core.requests.CheckLoggedUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CheckLoggedUserResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.GetCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.CheckLoggedUserService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetCurrentUserService;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.utils.WebUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class SystemSettingsMenuController {

    @Autowired
    private CheckLoggedUserService checkLoggedUserService;

    @GetMapping(value = "/systemSettingsMenu")
    public String systemSettingsMenu(HttpSession session) {
        if (!WebUI.checkIfUserIsLoggedIn(checkLoggedUserService, session.getId())) {
            return "errorNotLoggedIn";
        }

        return "systemSettingsMenu";
    }
}
