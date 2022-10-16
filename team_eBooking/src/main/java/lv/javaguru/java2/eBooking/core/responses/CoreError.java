package lv.javaguru.java2.eBooking.core.responses;

import lv.javaguru.java2.eBooking.core.services.appointment.add.AppointmentValidationResult;
import lv.javaguru.java2.eBooking.core.services.client.add.ClientValidationResult;

public class CoreError {
    private String field;
    private ClientValidationResult clientValidationMessage;
    private AppointmentValidationResult appointmentValidationMessage ;

    public CoreError(String field, ClientValidationResult clientValidationMessage) {
        this.field = field;
        this.clientValidationMessage = clientValidationMessage;
    }
    public CoreError(String field, AppointmentValidationResult appointmentValidationMessage){
        this.field=field;
        this.appointmentValidationMessage= appointmentValidationMessage;
    }

    public String getField() {
        return field;
    }

    public ClientValidationResult getClientValidationMessage() {
        return clientValidationMessage;
    }

    public AppointmentValidationResult getAppointmentValidationMessage() {
        return appointmentValidationMessage;
    }
}
