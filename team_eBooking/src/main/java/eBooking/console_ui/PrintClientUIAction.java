package eBooking.console_ui;

import eBooking.service.GetAllClientsService;

public class PrintClientUIAction implements UIAction {
private GetAllClientsService getAllClientsService;

    public PrintClientUIAction(GetAllClientsService getAllClientsService) {
        this.getAllClientsService = getAllClientsService;
    }

    public void execute() {
        System.out.println("Client list");
        getAllClientsService.execute();
    }
}
