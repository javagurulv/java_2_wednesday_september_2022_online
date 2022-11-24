package apartment.core.services;

import apartment.Apartment;
import apartment.core.database.Database;

import java.util.List;

public class PrintAllApartmentService {

    private Database database;

    public PrintAllApartmentService(Database database){
        this.database = database;
    }

    public List<Apartment> execute (){
        return database.getAllApartment();
    }

}
