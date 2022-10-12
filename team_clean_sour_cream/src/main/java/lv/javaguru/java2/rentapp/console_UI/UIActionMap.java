package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.rentapp.core.services.*;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.SearchVehicleRequestOrderingValidator;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.SearchVehicleRequestPagingValidator;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.SearchVehicleValidator;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators.SearchVehicleFieldsValidatorMap;

import java.util.HashMap;
import java.util.Map;

public class UIActionMap {

    private Map<Integer, UIAction> uiActionMap;
    private Database database = new InMemoryDatabaseImpl();
    private SearchVehicleValidator searchVehicleValidator = new SearchVehicleValidator(new SearchVehicleFieldsValidatorMap(),
            new SearchVehicleRequestOrderingValidator(), new SearchVehicleRequestPagingValidator());
    private AddVehicleService addNewVehicleService = new AddVehicleService(database);
    private DeleteVehicleByPlateNumberService deleteVehicleByPlateNumberService = new DeleteVehicleByPlateNumberService(database);
    private ShowAllVehiclesService showAllVehiclesService = new ShowAllVehiclesService(database);
    private ExitProgramService exitProgramService = new ExitProgramService();
    private SearchVehicleService searchVehicleService = new SearchVehicleService(database, searchVehicleValidator);


    public UIActionMap() {
        this.uiActionMap = new HashMap<>();
        uiActionMap.put(1, new AddVehicleUIAction(addNewVehicleService));
        uiActionMap.put(2, new DeleteVehicleByPlateNumberUIAction(deleteVehicleByPlateNumberService));
        uiActionMap.put(3, new ShowAllVehiclesUIAction(showAllVehiclesService));
        uiActionMap.put(4, new SearchVehicleUIAction(searchVehicleService));
        uiActionMap.put(5, new ExitProgramUIAction(exitProgramService));
    }

    public UIAction getAction(int userChoice) {
        return uiActionMap.get(userChoice);
    }

    public Integer getUIActionMapSize() {
        return uiActionMap.size();
    }
}

