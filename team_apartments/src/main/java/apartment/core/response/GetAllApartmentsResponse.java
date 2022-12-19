package apartment.core.response;

import apartment.Apartment;

import java.util.List;

public class GetAllApartmentsResponse extends CoreResponse{

    private List<Apartment> apartments;


    public GetAllApartmentsResponse(List<Apartment> apartments){
        this.apartments = apartments;
    }

    public List<Apartment> getApartments(){
        return apartments;
    }

}
