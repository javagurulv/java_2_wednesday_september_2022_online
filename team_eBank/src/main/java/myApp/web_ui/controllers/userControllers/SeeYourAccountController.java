package myApp.web_ui.controllers.userControllers;

import myApp.core.requests.SeeYourAccountRequest;
import myApp.core.responses.SeeYourAccountResponse;
import myApp.core.services.SeeYourAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeeYourAccountController {

    @Autowired
    private SeeYourAccountService service;

    @GetMapping(value = "/seeYourAccount")
    public String showOpenAccountPage(ModelMap modelMap) {
        SeeYourAccountResponse response = service.execute(new SeeYourAccountRequest(getUserName()));
        modelMap.addAttribute("bankAccount", response.getBankAccount().get());
        return "/seeYourAccount";
    }

    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}