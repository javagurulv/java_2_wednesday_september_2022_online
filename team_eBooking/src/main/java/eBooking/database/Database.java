package eBooking.database;

import eBooking.Appointment;
import eBooking.Client;

import java.util.List;

public interface Database {

    void saveClient(Client client);

    boolean deleteClientById(Long id);

    List<Client> getAllClients();

    void saveAppointment(Appointment appointment);

    boolean deleteAppointmentById(Long id);

    List<Appointment> getAllAppointments();

}
