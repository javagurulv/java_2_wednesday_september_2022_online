package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.rentapp.core.services.AddVehicleService;
import lv.javaguru.java2.rentapp.core.services.DeleteVehicleByPlateNumberService;
import lv.javaguru.java2.rentapp.core.services.ExitProgramService;
import lv.javaguru.java2.rentapp.core.services.ShowAllVehiclesService;


import java.util.HashMap;
import java.util.Map;

public class UIActionMap {

    private Database database = new InMemoryDatabaseImpl();
    private Map<Integer, UIAction> uiActionMap;
    private AddVehicleService addVehicleServiceMap = new AddVehicleService(database);
    private DeleteVehicleByPlateNumberService deleteVehicleByPlateNumberService = new DeleteVehicleByPlateNumberService(database);
    private ShowAllVehiclesService showAllVehiclesService = new ShowAllVehiclesService(database);
    private ExitProgramService exitProgramService = new ExitProgramService();


    public UIActionMap() {
        uiActionMap = new HashMap<>();
        uiActionMap.put(1, new AddNewVehicleUIAction(addVehicleServiceMap));
        uiActionMap.put(2, new DeleteVehicleByPlateNumberUIAction(deleteVehicleByPlateNumberService));
        uiActionMap.put(3, new ShowAllVehiclesUIAction(showAllVehiclesService));
        uiActionMap.put(4, new ExitProgramUIAction(exitProgramService));
    }

    public UIAction getAction(int userChoice) {
        return uiActionMap.get(userChoice);
    }
}

