package myApp.web_ui.controllers.userControllers;

import myApp.core.requests.TakeALoanRequest;
import myApp.core.responses.TakeALoanResponses;
import myApp.core.services.TakeALoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class TakeALoanController {

    @Autowired
    private TakeALoanService service;

    @GetMapping(value = "/takeALoan")
    public String showTakeALoanPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new TakeALoanRequest());
        return "takeALoan";
    }

    @PostMapping(value = "/takeALoan")
    public String takeALoanPageProcess(@ModelAttribute(value = "request") TakeALoanRequest request,
                                       ModelMap modelMap) {
        TakeALoanResponses responses = service.execute(request);
        if (responses.hasErrors()) {
            return "takeALoan";
        } else {
            return "redirect:/user";
        }
    }
}