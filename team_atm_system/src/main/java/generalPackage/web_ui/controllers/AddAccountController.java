package generalPackage.web_ui.controllers;

import generalPackage.core.requests.adminRequests.AddAccountRequest;
import generalPackage.core.responses.adminResponses.AddAccountResponse;
import generalPackage.core.services.adminOperations.AddAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddAccountController {

    @Autowired
    private AddAccountService addAccountService;

    @GetMapping(value = "/addAccountToList")
    public String showAddAccountPage (ModelMap modelMap){
        modelMap.addAttribute("request", new AddAccountRequest());
        return "addAccountToList";
    }

    @PostMapping ("/addAccountToList")
    public String processAddAccountRequest (@ModelAttribute(value = "request") AddAccountRequest request,ModelMap modelMap){
        AddAccountResponse response = addAccountService.execute(request);
        if (response.hasErrors()){
            modelMap.addAttribute("errors",response.getErrors());
            return "addAccountToList";}
        else {
            return "redirect:/";
        }
    }

}
