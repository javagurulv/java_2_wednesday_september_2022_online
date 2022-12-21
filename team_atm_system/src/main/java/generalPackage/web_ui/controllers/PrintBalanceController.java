package generalPackage.web_ui.controllers;

import generalPackage.core.requests.usersRequests.PrintBalanceRequest;
import generalPackage.core.responses.usersResponses.PrintBalanceResponse;
import generalPackage.core.services.usersOperations.PrintBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PrintBalanceController {

    @Autowired
    PrintBalance printBalance;

    @GetMapping(value = "/printBalanceItem")
    public String printBalancePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new PrintBalanceRequest());
        return "/printBalanceItem";
    }

    @PostMapping("/printBalanceItem")
    public String processPrintBalanceRequest(@ModelAttribute(value = "request") PrintBalanceRequest request, ModelMap modelMap) {
        PrintBalanceResponse response = printBalance.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/printBalanceItem";
        } else {
            return "redirect:/";

        }
    }
}
