package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.requests.client_request.ClientGetAllRequest;
import lv.javaguru.java2.eBooking.core.responses.client.ClientsGetAllResponse;
import lv.javaguru.java2.eBooking.core.services.client.ClientGetAllService;
import lv.javaguru.java2.eBooking.dependency_injection.DIComponent;
import org.springframework.stereotype.Component;

@Component
public class PrintClientUIAction implements UIAction {
private ClientGetAllService clientGetAllService;

    public PrintClientUIAction(ClientGetAllService clientGetAllService) {
        this.clientGetAllService = clientGetAllService;
    }

    public void execute() {
        System.out.println("Client list");
        ClientGetAllRequest request = new ClientGetAllRequest();
        ClientsGetAllResponse response = clientGetAllService.execute(request);
        response.getClients().forEach(System.out::println);
    }
}
