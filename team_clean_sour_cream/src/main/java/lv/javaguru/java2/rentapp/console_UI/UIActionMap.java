package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.rentapp.core.services.*;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.VehicleCreatorMap;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.SearchVehicleRequestOrderingValidator;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.SearchVehicleRequestPagingValidator;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.SearchVehicleValidator;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators.SearchVehicleFieldsValidatorMap;
import lv.javaguru.java2.rentapp.core.services.validators.DeleteVehicleByPlateNumberRequestValidator;
import lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators.AddVehicleValidatorMap;

import java.util.HashMap;
import java.util.Map;

public class UIActionMap {

    private Map<Integer, UIAction> uiActionMap;
    private Database database = new InMemoryDatabaseImpl();
    private DeleteVehicleByPlateNumberRequestValidator deleteVehicleByPlateNumberValidator = new DeleteVehicleByPlateNumberRequestValidator(database);
    private SearchVehicleValidator searchVehicleValidator = new SearchVehicleValidator(new SearchVehicleFieldsValidatorMap(),
            new SearchVehicleRequestOrderingValidator(), new SearchVehicleRequestPagingValidator());
    private VehicleCreatorMap vehicleCreatorMap = new VehicleCreatorMap();
    private AddVehicleValidatorMap vehicleValidatorMap = new AddVehicleValidatorMap(database);
    private AddVehicleService addNewVehicleService = new AddVehicleService(vehicleCreatorMap, vehicleValidatorMap, database);
    private DeleteVehicleByPlateNumberService deleteVehicleByPlateNumberService = new DeleteVehicleByPlateNumberService(database, deleteVehicleByPlateNumberValidator);
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

