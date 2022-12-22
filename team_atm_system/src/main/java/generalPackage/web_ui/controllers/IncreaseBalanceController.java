package generalPackage.web_ui.controllers;

import generalPackage.core.requests.usersRequests.IncreaseBalanceRequest;
import generalPackage.core.responses.usersResponses.IncreaseBalanceResponse;
import generalPackage.core.services.usersOperations.IncreaseBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IncreaseBalanceController {

    @Autowired
    private IncreaseBalance increaseBalance;

    @GetMapping(value = "/increaseBalanceItem")
    public String showIncreaseBalancePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new IncreaseBalanceRequest());
        return "increaseBalanceItem";
    }

    @PostMapping("/increaseBalanceItem")
    public String processIncreaseBalanceRequest(@ModelAttribute(value = "request") IncreaseBalanceRequest request, ModelMap modelMap) {
        IncreaseBalanceResponse response = increaseBalance.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "increaseBalanceItem";
        } else {
            return "redirect:/";
        }
    }
}
