package lv.javaguru.java2.eBooking.core.database;

import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.services.validators.ClientValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryDatabase implements Database {
    private Long nextId = 1L;
    private List<Client> clients = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public void saveClient(Client client) throws RuntimeException {
        if (!isClientDuplicated(client)) {
            client.setId(nextId);
            nextId++;
            clients.add(client);
        } else {
            throw new RuntimeException("Found duplicated data, try again...");
        }
    }

    @Override
    public boolean isClientDuplicated(Client client) {
        return clients.stream()
                .anyMatch(client1 -> client1.getClientEmail().equals(client.getClientEmail())
                        || client1.getClientPhoneNumber().equals(client.getClientPhoneNumber()));
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
        Optional<Appointment> appointmentToBeRemoved = appointments.stream()
                .filter(appointment -> appointment.getId().equals(id))
                .findFirst();
        if (appointmentToBeRemoved.isPresent()) {
            Appointment appointmentToRemove = appointmentToBeRemoved.get();
            isAppointmentDeleted = appointments.remove(appointmentToRemove);
        }
        return isAppointmentDeleted;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    @Override
    public List<Client> findClientByEMail(String clientEmail) {
        return clients.stream()
                .filter(client -> client.getClientEmail().equals(clientEmail))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> findClientByPhoneNumber(String clientPhoneNumber) {
        return clients.stream()
                .filter(client -> client.getClientPhoneNumber().equals(clientPhoneNumber))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> findClientByEmailAndPhoneNumber(String clientEmail, String clientPhoneNumber) {
        return clients.stream()
                .filter(client -> client.getClientEmail().equals(clientEmail)
                        && client.getClientPhoneNumber().equals(clientPhoneNumber))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findAppointmentByMasterName(String masterName) {
        return appointments.stream()
                .filter(appointment -> appointment.getMasterName().equals(masterName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findAppointmentByTypeOfService(String typeOFService) {
        return appointments.stream()
                .filter(appointment -> appointment.getTypeOfService().equals(typeOFService))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findAppointmentByMasterNameAndTypeOfService(String masterName, String typeOfService) {
        return appointments.stream()
                .filter(appointment -> appointment.getMasterName().equals(masterName)
                        && appointment.getTypeOfService().equals(typeOfService))
                .collect(Collectors.toList());
    }
}
