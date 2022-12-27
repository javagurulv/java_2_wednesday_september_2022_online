package lv.javaguru.java2.tasksScheduler.web_ui.controllers;


import lv.javaguru.java2.tasksScheduler.core.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.SettingsLoginRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.LoginResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.SettingsLoginResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.LoginService;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.SettingsLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminLoginController {

    @Autowired
    private SettingsLoginService settingsLoginService;

    @GetMapping(value = "/adminLogin")
    public String showAdminLoginPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SettingsLoginRequest());
        return "adminLogin";
    }

    @PostMapping("/adminLogin")
    public String processAdminLoginRequest(@ModelAttribute(value = "request") SettingsLoginRequest request, ModelMap modelMap) {
        SettingsLoginResponse response = settingsLoginService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "adminLogin";
        } else {
            return "redirect:systemSettingsMenu";
        }
    }

}
