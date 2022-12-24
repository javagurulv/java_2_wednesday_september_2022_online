package myApp.web_ui.controllers.userControllers;

import myApp.core.requests.CloseAccountRequest;
import myApp.core.responses.CloseAccountResponse;
import myApp.core.services.CloseAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CloseAccountController {
    @Autowired
    private CloseAccountService service;

    @GetMapping(value = "/closeAccount")
    public String showOpenAccountPage(ModelMap modelMap) {
        CloseAccountRequest request = new CloseAccountRequest(getUserName());
        CloseAccountResponse response = service.execute(request);
        if (!response.isDeleted()) {
            return "redirect:/user";
        } else {
            return "closeAccount";
        }
    }
    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
