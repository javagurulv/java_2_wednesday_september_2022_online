package apartment.core.response;

import apartment.Apartment;

import java.util.List;

public class AddApartmentResponse extends CoreResponse{

    private Apartment newApartment;

    public AddApartmentResponse (List<CoreError> errors){
        super(errors);
    }

    public AddApartmentResponse (Apartment newApartment){
        this.newApartment = newApartment;
    }

    public Apartment getNewApartment() {
        return  newApartment;
    }

}
