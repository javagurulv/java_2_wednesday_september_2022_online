package myApp.web_ui.controllers.adminControllers;

import myApp.core.domain.User;
import myApp.core.requests.GetAllUsersRequest;
import myApp.core.responses.GetAllUsersResponse;
import myApp.core.services.GetAllUsersService;
import myApp.web_ui.security.EncoderAndDecoderPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class GetAllUsersController {

    @Autowired
    private GetAllUsersService service;

    @Autowired
    private EncoderAndDecoderPassword decoder;

    @GetMapping(value = "/getAllUsers")
    public String getAllUsers(ModelMap modelMap) {
        GetAllUsersResponse response = service.execute(new GetAllUsersRequest());
        List<User> usersDecoded = new ArrayList<>();
        for (User user : response.getUsers()) {
            user.setPersonalCode(decoder.executeDecode(user.getPersonalCode()));
            user.setPassword(decoder.executeDecode(user.getPassword()));
            user.setRole(decoder.executeDecode(user.getRole()));
            usersDecoded.add(user);
        }
        modelMap.addAttribute("users", response.getUsers());
        return "/getAllUsers";
    }
}