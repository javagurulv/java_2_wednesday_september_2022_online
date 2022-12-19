package myApp.web_ui.controllers.userControllers;

import myApp.core.requests.MoneyTransferRequest;
import myApp.core.responses.MoneyTransferResponse;
import myApp.core.services.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MoneyTransferController {
    @Autowired
    private MoneyTransferService service;

    @GetMapping(value = "/moneyTransfer")
    public String showAddBankAccountPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new MoneyTransferRequest());
        return "moneyTransfer";
    }

    @PostMapping("/moneyTransfer")
    public String processAddBankAccountRequest(@ModelAttribute(value = "request")  MoneyTransferRequest request,
                                               ModelMap modelMap) {
        MoneyTransferResponse response = service.execute(request,getUsername());
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "moneyTransfer";
        } else {
            return "redirect:/user";
        }
    }

    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return username;
    }
}