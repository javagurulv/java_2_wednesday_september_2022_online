package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.services.ShowAllVehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowAllVehiclesUIAction implements UIAction {
    @Autowired
    private ShowAllVehiclesService showAllVehiclesService;

    @Override
    public void execute() {
        System.out.println("All vehicles list: ");
        showAllVehiclesService.execute().getVehicles().forEach(System.out::println);
        System.out.println("end of list.");
    }
}
