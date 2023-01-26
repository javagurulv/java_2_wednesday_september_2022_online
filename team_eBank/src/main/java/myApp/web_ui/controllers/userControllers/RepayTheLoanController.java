package myApp.web_ui.controllers.userControllers;

import myApp.core.requests.RepayTheLoanRequest;
import myApp.core.requests.TakeALoanRequest;
import myApp.core.responses.RepayTheLoanResponse;
import myApp.core.responses.TakeALoanResponses;
import myApp.core.services.RepayTheLoanService;
import myApp.core.services.TakeALoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RepayTheLoanController {

    @Autowired
    private RepayTheLoanService service;

    @GetMapping(value = "/repayTheLoan")
    public String showTakeALoanPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RepayTheLoanRequest());
        return "repayTheLoan";
    }

    @PostMapping(value = "/repayTheLoan")
    public String takeALoanPageProcess(@ModelAttribute(value = "request") RepayTheLoanRequest request,
                                       ModelMap modelMap) {
        RepayTheLoanResponse responses = service.execute(request);
        if (responses.hasErrors()) {
            return "repayTheLoan";
        } else {
            return "repayTheLoanSuccess";
        }
    }
}