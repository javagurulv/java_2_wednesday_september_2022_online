package myApp.web_ui.controllers.adminControllers;

import liquibase.pro.packaged.A;
import liquibase.pro.packaged.S;
import myApp.core.requests.DeleteUserRequest;
import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.responses.DeleteUserResponses;
import myApp.core.services.DeleteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteUserController {

    @Autowired
    private DeleteUserService service;

    @GetMapping(value = "/deleteUser")
    public String showDeleteUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteUserRequest());
        return "deleteUser";
    }

    @PostMapping(value = "/deleteUser")
    public String processDeleteUserRequest(@ModelAttribute(value = "request")DeleteUserRequest request,
                                           ModelMap modelMap) {
        DeleteUserResponses responses = service.execute(request);
        if (responses.hasErrors()) {
            modelMap.addAttribute("errors", responses.getErrors());
            return "deleteUser";
        } else {
            return "deleteUserSuccess";
        }
    }
}
