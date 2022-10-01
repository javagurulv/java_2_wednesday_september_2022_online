package eBooking.database;

import eBooking.Appointment;
import eBooking.Client;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase implements Database {
    private Long nextId = 1L;
    private List<Client> clients = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public void saveClient(Client client) {
        client.setId(nextId);
        ++nextId;
        clients.add(client);
    }

    @Override
    public void deleteClientById(Long id) {
        clients.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst()
                .ifPresent(client -> clients.remove(client));
    }

    @Override
    public List<Client> getAllClients() {
        return clients;
    }

    @Override
    public void saveAppointment(Appointment appointment) {
        appointment.setId(nextId);
        ++nextId;
        appointments.add(appointment);
    }

    @Override
    public void deleteAppointmentById(Long id) {
    appointments.stream()
            .filter(appointment -> appointment.getId().equals(id))
            .findFirst()
            .ifPresent(appointment -> appointments.remove(appointment));
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointments;
    }
}
