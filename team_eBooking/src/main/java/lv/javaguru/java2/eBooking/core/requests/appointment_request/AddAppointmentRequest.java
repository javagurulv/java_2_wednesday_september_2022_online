package lv.javaguru.java2.eBooking.core.requests.appointment_request;

public class AddAppointmentRequest {

    private String masterName;
    private String typeOfService;

    public AddAppointmentRequest(String masterName, String typeOfService) {
        this.masterName = masterName;
        this.typeOfService = typeOfService;
    }

    public String getMasterName() {
        return masterName;
    }

    public String getTypeOfService() {
        return typeOfService;
    }
}
