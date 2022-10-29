package lv.javaguru.java2.eBooking.core.services.client;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientRemoveRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.client.ClientRemoveResponse;
import lv.javaguru.java2.eBooking.core.services.validators.ClientValidationResult;
import lv.javaguru.java2.eBooking.core.services.validators.ClientRemoveValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClientRemoveServiceTest {

    @Mock
    private Database database;

    @Mock
    private ClientRemoveValidator validator;

    @InjectMocks
    private ClientRemoveService service;

    @Test
    public void shouldReturnResponseWithErrorWhenClientIdIsNotProvided(){
        ClientRemoveRequest request = new ClientRemoveRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Client Id",ClientValidationResult.CLIENT_ID_MUST_NOT_BE_EMPTY));
        Mockito.
                when(validator.validate(request)).
                thenReturn(errors);
        ClientRemoveResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"Id");
        assertEquals(response.getErrors().get(0).getClientValidationMessage(),
                ClientValidationResult.CLIENT_ID_MUST_NOT_BE_EMPTY);
    }

    @Test
    public void shouldDeleteClientFromDatabase(){
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.deleteClientById(1L)).thenReturn(true);
        ClientRemoveRequest request = new ClientRemoveRequest(1L);
        ClientRemoveResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertTrue(response.isClientRemoved());
    }
}