package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import lv.javaguru.java2.tasksScheduler.core.requests.DeleteCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetUsersRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.core.responses.DeleteCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.GetUsersResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.DeleteCurrentUserService;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.GetUsersService;
import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserDeletionController {
    @Autowired
    private DeleteCurrentUserService deleteCurrentUserService;

    @GetMapping(value = "/userDeletion")
    public String showUserDeletionPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteCurrentUserRequest());
        modelMap.addAttribute("message",
                "Please confirm that you wish to delete your account from the system.");
        modelMap.addAttribute("stage", "confirmation");
        return "userDeletion";
    }

    @PostMapping("/userDeletion")
    public String processDeletionRequest(@ModelAttribute(value = "request") DeleteCurrentUserRequest request, ModelMap modelMap) {
        DeleteCurrentUserResponse response = deleteCurrentUserService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            String msg = "User " + "'" + response.getDeletedUserName() + "'" +
                    " has been successfully deleted.";
            modelMap.addAttribute("message", msg);
            modelMap.addAttribute("stage", "result");
        }
        return "userDeletion";
    }
}
