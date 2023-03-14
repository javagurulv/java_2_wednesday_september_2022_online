package lv.javaguru.java2.tasksScheduler.web_ui.controllers;


import lv.javaguru.java2.tasksScheduler.core.requests.ExitSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.ExitSettingsResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.SettingsLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SettingsLogoutController {

    @Autowired
    SettingsLogoutService settingsLogoutService;

    @GetMapping(value = "/settingsLogout")
    public String settingsLogout(HttpSession session) {
        ExitSettingsRequest request = new ExitSettingsRequest(session.getId());
        ExitSettingsResponse response = settingsLogoutService.execute(request);
        return "redirect:/";
    }
}
