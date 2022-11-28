package myApp.web_ui.controllers;
/*
import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.OpenAccountResponse;
import myApp.core.services.OpenAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OpenAccountController {

    @Autowired
    private OpenAccountService service;

    @Autowired
    InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @GetMapping(value = "/openAccount")
    public String showOpenAccountPage(ModelMap modelMap) {
        OpenAccountRequest request = new OpenAccountRequest(inMemoryUserDetailsManager..getUsername());
        OpenAccountResponse response = service.execute(request);
        if (response.hasErrors()) {
            return "openAccount";
        } else {
            return "redirect:/admin";
        }
    }

}

 */
