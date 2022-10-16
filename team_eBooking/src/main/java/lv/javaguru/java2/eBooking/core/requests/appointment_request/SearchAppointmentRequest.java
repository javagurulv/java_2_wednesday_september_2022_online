package lv.javaguru.java2.eBooking.core.requests.appointment_request;

public class SearchAppointmentRequest {
    private String masterName;
    private String typeOfService;

    public SearchAppointmentRequest(String masterName, String typeOfService) {
        this.masterName = masterName;
        this.typeOfService = typeOfService;
    }

    public String getMasterName() {
        return masterName;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public boolean isMasterNameProvided(){
        return this.masterName!=null && !this.masterName.isEmpty();
    }

    public boolean isTypeOfServiceProvided(){
        return  this.typeOfService!=null && !this.typeOfService.isEmpty();
    }
}
