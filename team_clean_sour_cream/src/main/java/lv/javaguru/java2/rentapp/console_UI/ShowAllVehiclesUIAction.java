package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.ShowAllVehiclesRequest;
import lv.javaguru.java2.rentapp.core.responses.ShowAllVehiclesResponse;
import lv.javaguru.java2.rentapp.core.services.ShowAllVehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowAllVehiclesUIAction implements UIAction {
    @Autowired
    private ShowAllVehiclesService showAllVehiclesService;

    @Override
    public void execute() {

        ShowAllVehiclesRequest request = new ShowAllVehiclesRequest();
        ShowAllVehiclesResponse response = showAllVehiclesService.execute(request);
        if (response.getMsg() != null) {
            System.out.println(response.getMsg());
        } else {
            System.out.println("All vehicles list: ");
            response.getVehicles().forEach(System.out::println);
            System.out.println("end of list.");
        }
    }

}
