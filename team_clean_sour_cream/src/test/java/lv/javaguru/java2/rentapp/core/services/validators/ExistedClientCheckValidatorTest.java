package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.database.ClientDatabase;
import lv.javaguru.java2.rentapp.core.requests.AddClientRequest;
import lv.javaguru.java2.rentapp.core.services.ExistedClientCheckService;
import lv.javaguru.java2.rentapp.core.services.validators.add_client_validators.ExistedClientCheckValidator;
import lv.javaguru.java2.rentapp.domain.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ExistedClientCheckValidatorTest {

    @Mock
    private Client client;

    @Mock
    private ClientDatabase clientDatabase;

    @Mock
    private ExistedClientCheckService exClientChkService;

    @Mock
    private AddClientRequest addClientRequest;

    @InjectMocks
    private ExistedClientCheckValidator existedClientCheckValidator;

    @Test
    void first() {
        Mockito.when(exClientChkService.checkClientExistenceByPersonalId(addClientRequest)).thenReturn(Optional.of(1L));
        Mockito.when(exClientChkService.checkClientExistenceByEmail(addClientRequest)).thenReturn(Optional.of(1L));
        Mockito.when(clientDatabase.getById(Mockito.any())).thenReturn(Optional.of(client));
        Mockito.when(client.getFirstName()).thenReturn("a");
        Mockito.when(client.getLastName()).thenReturn("b");

    }
    @Test
    void second() {
        Mockito.when(exClientChkService.checkClientExistenceByPersonalId(addClientRequest)).thenReturn(Optional.of(1L));
        Mockito.when(exClientChkService.checkClientExistenceByEmail(addClientRequest)).thenReturn(Optional.of(1L));
        Mockito.when(clientDatabase.getById(Mockito.any())).thenReturn(Optional.of(client));
        Mockito.when(client.getFirstName()).thenReturn("a");
        Mockito.when(client.getLastName()).thenReturn("b");

    }

}