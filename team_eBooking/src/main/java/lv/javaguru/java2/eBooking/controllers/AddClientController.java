package lv.javaguru.java2.eBooking.controllers;

import lv.javaguru.java2.eBooking.core.requests.client_request.ClientAddRequest;
import lv.javaguru.java2.eBooking.core.services.client.ClientAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddClientController {
    @Autowired
    private ClientAddService clientAddService;

    @GetMapping(value = "/addClient")
    public String showAddedClient(ModelMap modelMap){
        modelMap.addAttribute("request", new ClientAddRequest());
        return "addClient";
    }

    @PostMapping("/addClient")
    public String processAddClientRequest(@ModelAttribute(value = "request") ClientAddRequest request, ModelMap modelMap) {
        clientAddService.execute(request);
        return "index";
    }
}
