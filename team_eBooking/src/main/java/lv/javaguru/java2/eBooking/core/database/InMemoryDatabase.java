package lv.javaguru.java2.eBooking.core.database;

import lv.javaguru.java2.eBooking.Appointment;
import lv.javaguru.java2.eBooking.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryDatabase implements Database {
    private Long nextId = 1L;
    private List<Client> clients = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public void saveClient(Client client) {
        client.setId(nextId);
        nextId++;
        clients.add(client);
    }

    @Override
    public boolean deleteClientById(Long id) {

        boolean isClientRemoved = false;
        Optional<Client> clientToBeRemoved = clients.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst();
        if (clientToBeRemoved.isPresent()) {
            Client clientToRemove = clientToBeRemoved.get();
            isClientRemoved = clients.remove(clientToRemove);
        }
        return isClientRemoved;

    }

    @Override
    public List<Client> getAllClients() {
        return clients;
    }

    @Override
    public void saveAppointment(Appointment appointment) {
        appointment.setId(nextId);
        nextId++;
        appointments.add(appointment);
    }

    @Override
    public boolean deleteAppointmentById(Long id) {
        boolean isAppointmentDeleted = false;
        Optional <Appointment> appointmentToBeRemoved =  appointments.stream()
                .filter(appointment -> appointment.getId().equals(id))
                .findFirst();
                if(appointmentToBeRemoved.isPresent()){
                    Appointment appointmentToRemove = appointmentToBeRemoved.get();
                    isAppointmentDeleted=appointments.remove(appointmentToRemove);
                }
                return isAppointmentDeleted;
    }


    @Override
    public List<Appointment> getAllAppointments() {
        return appointments;
    }
}
