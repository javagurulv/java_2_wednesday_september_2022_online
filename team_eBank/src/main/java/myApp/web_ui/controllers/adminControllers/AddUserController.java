package myApp.web_ui.controllers.adminControllers;

import myApp.core.requests.AddBankAccountRequest;
import myApp.core.requests.AddUserRequest;
import myApp.core.responses.AddBankAccountResponse;
import myApp.core.responses.AddUserResponse;
import myApp.core.services.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddUserController {

    @Autowired
    private AddUserService addUserService;

    @GetMapping(value = "/addUser")
    public String showAddUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddUserRequest());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String processAddUserRequest(@ModelAttribute(value = "request") AddUserRequest request,
                                        ModelMap modelMap) {
        AddUserResponse response = addUserService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addUser";
        } else {
            return "addUserSuccess";
        }
    }
}