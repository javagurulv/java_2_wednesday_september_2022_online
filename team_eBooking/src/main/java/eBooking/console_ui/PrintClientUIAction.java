package eBooking.console_ui;

import eBooking.service.GetAllClientService;

public class PrintClientUIAction implements UIAction {
private GetAllClientService getAllClientService;

    public PrintClientUIAction(GetAllClientService getAllClientService) {
        this.getAllClientService = getAllClientService;
    }

    public void execute() {
        System.out.println("Client list");
        getAllClientService.execute();
    }
}
