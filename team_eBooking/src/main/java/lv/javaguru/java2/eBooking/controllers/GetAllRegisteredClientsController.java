package lv.javaguru.java2.eBooking.controllers;

import lv.javaguru.java2.eBooking.core.requests.client_request.ClientGetAllRequest;
import lv.javaguru.java2.eBooking.core.responses.client.ClientsGetAllResponse;
import lv.javaguru.java2.eBooking.core.services.client.ClientGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetAllRegisteredClientsController {

    @Autowired
    private ClientGetAllService clientGetAllService;

    @GetMapping(value = "/registeredClientsList")
    public String showAllRegisteredClients(ModelMap modelMap){
        ClientsGetAllResponse response = clientGetAllService.execute(new ClientGetAllRequest());
        modelMap.addAttribute("clients",response.getClients());
        return "registeredClientsList";
    }
}
