package lv.javaguru.java2.eBooking.core.requests.appointment_request;

public class SearchAppointmentRequest {
    private String masterName;
    private String typeOfService;
    private Ordering ordering;

    public SearchAppointmentRequest(String masterName, String typeOfService, Ordering ordering) {
        this.masterName = masterName;
        this.typeOfService = typeOfService;
        this.ordering = ordering;
    }

    public String getMasterName() {
        return masterName;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public boolean isMasterNameProvided(){
        return this.masterName!=null && !this.masterName.isEmpty();
    }

    public boolean isTypeOfServiceProvided(){
        return  this.typeOfService!=null && !this.typeOfService.isEmpty();
    }
}
