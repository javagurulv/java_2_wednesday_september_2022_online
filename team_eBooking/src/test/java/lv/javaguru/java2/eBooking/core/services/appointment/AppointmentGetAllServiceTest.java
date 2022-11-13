package lv.javaguru.java2.eBooking.core.services.appointment;

import lv.javaguru.java2.eBooking.core.database.ClientRepository;
import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentGetAllRequest;
import lv.javaguru.java2.eBooking.core.responses.appointment.AppointmentGetAllResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentGetAllServiceTest {

    @Mock private ClientRepository clientRepository;
    @InjectMocks
    private AppointmentGetAllService service;

    @Test
    public void shouldGetAListOfAppointmentsFromDatabase(){
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(new Appointment("Master name", "Type of service"));
        Mockito.when(clientRepository.getAllAppointments()).thenReturn(appointmentList);
        AppointmentGetAllRequest request = new AppointmentGetAllRequest();
        AppointmentGetAllResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(response.getAppointmentList().size(),1);
        assertEquals(response.getAppointmentList().get(0).getMasterName(),"Master name");
        assertEquals(response.getAppointmentList().get(0).getTypeOfService(),"Type of service");
    }
}