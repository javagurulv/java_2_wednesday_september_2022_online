package generalPackage.web_ui.controllers;

import generalPackage.core.requests.adminRequests.GetAllAccountsRequest;
import generalPackage.core.responses.adminResponses.GetAllAccountsResponse;
import generalPackage.core.services.adminOperations.GetAllAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllAccountsController {

    @Autowired
    private GetAllAccountsService getAllAccountsService;

    @GetMapping(value = "/showAllAccounts")
    public String showAllAccounts(ModelMap modelMap) {
        GetAllAccountsResponse response = getAllAccountsService.execute(new GetAllAccountsRequest());
        modelMap.addAttribute("accounts", response.getAccounts());
        return"/showAllAccounts";
    }


}
