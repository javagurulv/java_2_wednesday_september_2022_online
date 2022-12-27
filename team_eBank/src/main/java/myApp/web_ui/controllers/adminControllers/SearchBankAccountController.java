package myApp.web_ui.controllers.adminControllers;

import myApp.core.requests.AddBankAccountRequest;
import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.SearchBankAccountResponse;
import myApp.core.services.SearchBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchBankAccountController {

    @Autowired
    private SearchBankAccountService service;

    @GetMapping(value = "/searchBankAccount")
    public String showSearchBankAccountPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchBankAccountRequest());
        return "searchBankAccount";
    }

    @PostMapping("/searchBankAccount")
    public String processSearchBankAccountRequest(@ModelAttribute(value = "request") SearchBankAccountRequest request,
                                               ModelMap modelMap) {
        SearchBankAccountResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "searchBankAccount";
        } else {
            modelMap.addAttribute("bank_accounts", response.getBankAccounts());
            return "searchBankAccountSuccess";
        }
    }
}
