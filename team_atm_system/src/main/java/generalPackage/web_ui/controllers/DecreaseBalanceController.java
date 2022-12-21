package generalPackage.web_ui.controllers;

import generalPackage.core.requests.usersRequests.DecreaseBalanceRequest;
import generalPackage.core.responses.usersResponses.DecreaseBalanceResponse;
import generalPackage.core.services.usersOperations.DecreaseBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DecreaseBalanceController {

    @Autowired
    private DecreaseBalance decreaseBalance;

    @GetMapping(value = "/decreaseBalanceItem")
    public String showDecreaseBalancePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DecreaseBalanceRequest());
        return "decreaseBalanceItem";
    }

    @PostMapping("/decreaseBalanceItem")
    public String processDecreaseBalanceRequest(@ModelAttribute(value = "request") DecreaseBalanceRequest request, ModelMap modelMap) {
        DecreaseBalanceResponse response = decreaseBalance.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "decreaseBalanceItem";
        } else {
            return "redirect:/";
        }
    }
}
