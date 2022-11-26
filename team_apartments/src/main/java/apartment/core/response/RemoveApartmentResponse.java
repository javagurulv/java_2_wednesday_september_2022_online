package apartment.core.response;

import java.util.List;

public class RemoveApartmentResponse extends CoreResponse{

    private boolean apartmentRemoved;

    public RemoveApartmentResponse(List<CoreError> errors){
        super(errors);
    }

    public RemoveApartmentResponse (boolean apartmentRemoved){
        this.apartmentRemoved = apartmentRemoved;
    }

    public boolean isApartmentRemoved(){
        return apartmentRemoved;
    }
}
