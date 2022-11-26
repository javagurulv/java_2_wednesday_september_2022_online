package myApp.web_ui.controllers;

import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.responses.RemoveBankAccountResponse;
import myApp.core.services.RemoveBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteBankAccountController {
    @Autowired
    private RemoveBankAccountService service;

    @GetMapping(value = "/deleteBankAccount")
    public String showDeleteBankAccountPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RemoveBankAccountRequest());
        return "deleteBankAccount";
    }

    @PostMapping("/deleteBankAccount")
    public String processDeleteBankAccountRequest(@ModelAttribute(value = "request") RemoveBankAccountRequest request,
                                               ModelMap modelMap) {
        RemoveBankAccountResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "deleteBankAccount";
        } else {
            return "redirect:/admin";
        }
    }

}
