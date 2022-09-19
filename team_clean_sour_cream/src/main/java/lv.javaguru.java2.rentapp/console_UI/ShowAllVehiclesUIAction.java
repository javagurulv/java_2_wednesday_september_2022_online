package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.services.ShowAllVehiclesService;

public class ShowAllVehiclesUIAction implements UIAction {

    private ShowAllVehiclesService showAllVehiclesService;

    public ShowAllVehiclesUIAction(ShowAllVehiclesService showAllVehiclesService) {
        this.showAllVehiclesService = showAllVehiclesService;
    }

    @Override
    public void execute() {
        System.out.println("All vehicles list: ");
        showAllVehiclesService.execute().getVehicles().forEach(System.out::println);
        System.out.println("end of list.");
    }
}
