package apartment.core.response;

import apartment.Apartment;

import java.util.List;

public class SearchApartmentResponse extends CoreResponse{

    private List<Apartment> apartments;

    public SearchApartmentResponse (List<Apartment> apartments, List<CoreError> errors){
        super(errors);
        this.apartments = apartments;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }
}
