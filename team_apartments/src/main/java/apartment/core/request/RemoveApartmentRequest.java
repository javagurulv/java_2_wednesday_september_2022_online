package apartment.core.request;

public class RemoveApartmentRequest {

    private Long apartmentIdToRemove;

    public RemoveApartmentRequest (Long apartmentIdToRemove){
        this.apartmentIdToRemove = apartmentIdToRemove;
    }

    public Long getApartmentIdToRemove(){
        return apartmentIdToRemove;
    }

}
