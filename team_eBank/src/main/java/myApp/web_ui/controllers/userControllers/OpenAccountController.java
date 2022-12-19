package myApp.web_ui.controllers.userControllers;

import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.OpenAccountResponse;
import myApp.core.services.OpenAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OpenAccountController {
    @Autowired
    private OpenAccountService service;

    @GetMapping(value = "/openAccount")
    public String showOpenAccountPage(ModelMap modelMap) {
        OpenAccountRequest request = new OpenAccountRequest(getUserName());
        OpenAccountResponse response = service.execute(request);
        if (response.hasErrors()) {
            return "redirect:/user";
        } else {
            return "openAccount";
        }
    }

    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}