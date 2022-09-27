package lv.javaguru.java2.eBooking.database;

import lv.javaguru.java2.eBooking.Appointment;
import lv.javaguru.java2.eBooking.Client;

import java.util.List;

public interface Database {

    void saveClient(Client client);

    void deleteClientById(Long id);

    List<Client> getAllClients();

    void saveAppointment(Appointment appointment);

    void deleteAppointmentById(Long id);

    List<Appointment> getAllAppointments();

}
