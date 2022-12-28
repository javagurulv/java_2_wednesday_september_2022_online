package lv.javaguru.java2.tasksScheduler.web_ui.controllers;


import lv.javaguru.java2.tasksScheduler.core.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.LoginResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/userLogin")
    public String showUserLoginPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new LoginRequest());
        return "userLogin";
    }

    @PostMapping("/userLogin")
    public String processLoginRequest(@ModelAttribute(value = "request") LoginRequest request, ModelMap modelMap) {
        LoginResponse response = loginService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "userLogin";
        } else {
            return "redirect:userMenu";
        }
    }

}
