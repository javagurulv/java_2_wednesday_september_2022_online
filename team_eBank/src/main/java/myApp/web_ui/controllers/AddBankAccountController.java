package myApp.web_ui.controllers;

import myApp.core.requests.AddBankAccountRequest;
import myApp.core.responses.AddBankAccountResponse;
import myApp.core.services.AddBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class AddBankAccountController {

    @Autowired
    private AddBankAccountService addBankAccountService;

    @GetMapping(value = "/addBankAccount")
    public String showAddBankAccountPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddBankAccountRequest());
        return "addBankAccount";
    }

    @PostMapping("/addBankAccount")
    public String processAddBankAccountRequest(@ModelAttribute(value = "request") AddBankAccountRequest request,
                                               ModelMap modelMap) {
        AddBankAccountResponse response = addBankAccountService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addBankAccount";
        } else {
            return "redirect:/admin";
        }
    }

}
