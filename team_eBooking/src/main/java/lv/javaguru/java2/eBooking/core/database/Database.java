package lv.javaguru.java2.eBooking.core.database;

import lv.javaguru.java2.eBooking.Appointment;
import lv.javaguru.java2.eBooking.Client;

import java.util.List;

public interface Database {

    void saveClient(Client client);

    boolean deleteClientById(Long id);

    List<Client> getAllClients();

    void saveAppointment(Appointment appointment);

    boolean deleteAppointmentById(Long id);

    List<Appointment> getAllAppointments();

}
