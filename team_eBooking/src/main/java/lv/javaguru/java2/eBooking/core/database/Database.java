package lv.javaguru.java2.eBooking.core.database;

import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.services.validators.ClientValidationResult;

import java.util.List;

public interface Database {

   void saveClient(Client client);

    boolean deleteClientById(Long id);

    List<Client> getAllClients();

    void saveAppointment(Appointment appointment);

    boolean deleteAppointmentById(Long id);

    List<Appointment> getAllAppointments();
    
    List<Client> findClientByEMail(String clientEmail);

    List <Client> findClientByPhoneNumber(String clientPhoneNumber);

    List<Client> findClientByEmailAndPhoneNumber(String clientEmail, String clientPhoneNumber);

    List <Appointment> findAppointmentByMasterName(String masterName);

    List <Appointment> findAppointmentByTypeOfService(String typeOFService);

    List<Appointment> findAppointmentByMasterNameAndTypeOfService( String masterName,
                                                                   String typeOfService);

}
