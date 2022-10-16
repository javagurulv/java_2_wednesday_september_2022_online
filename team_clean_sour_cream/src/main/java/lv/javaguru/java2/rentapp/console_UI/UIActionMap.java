package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.database.VehicleDatabaseImpl;
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
    private VehicleDatabase vehicleDatabase = new VehicleDatabaseImpl();
    private DeleteVehicleByPlateNumberRequestValidator deleteVehicleByPlateNumberValidator = new DeleteVehicleByPlateNumberRequestValidator(vehicleDatabase);
    private SearchVehicleValidator searchVehicleValidator = new SearchVehicleValidator(new SearchVehicleFieldsValidatorMap(),
            new SearchVehicleRequestOrderingValidator(), new SearchVehicleRequestPagingValidator());
    private VehicleCreatorMap vehicleCreatorMap = new VehicleCreatorMap();
    private AddVehicleValidatorMap vehicleValidatorMap = new AddVehicleValidatorMap(vehicleDatabase);
    private AddVehicleService addNewVehicleService = new AddVehicleService(vehicleCreatorMap, vehicleValidatorMap, vehicleDatabase);
    private DeleteVehicleByPlateNumberService deleteVehicleByPlateNumberService = new DeleteVehicleByPlateNumberService(vehicleDatabase, deleteVehicleByPlateNumberValidator);
    private ShowAllVehiclesService showAllVehiclesService = new ShowAllVehiclesService(vehicleDatabase);
    private ExitProgramService exitProgramService = new ExitProgramService();
    private SearchVehicleService searchVehicleService = new SearchVehicleService(vehicleDatabase, searchVehicleValidator);


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

