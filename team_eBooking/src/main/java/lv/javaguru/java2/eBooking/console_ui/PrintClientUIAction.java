package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.requests.client_request.GetAllClientsRequest;
import lv.javaguru.java2.eBooking.core.responses.client_response.GetAllClientsResponse;
import lv.javaguru.java2.eBooking.core.services.client_service.print_all.GetAllClientsService;

public class PrintClientUIAction implements UIAction {
private GetAllClientsService getAllClientsService;

    public PrintClientUIAction(GetAllClientsService getAllClientsService) {
        this.getAllClientsService = getAllClientsService;
    }

    public void execute() {
        System.out.println("Client list");
        GetAllClientsRequest request = new GetAllClientsRequest();
        GetAllClientsResponse response = getAllClientsService.execute(request);
        response.getClients().forEach(System.out::println);
    }
}
