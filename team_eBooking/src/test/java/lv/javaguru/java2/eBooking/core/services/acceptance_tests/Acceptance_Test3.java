package lv.javaguru.java2.eBooking.core.services.acceptance_tests;

import lv.javaguru.java2.eBooking.ApplicationContext;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientAddRequest;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientRemoveRequest;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientSearchRequest;
import lv.javaguru.java2.eBooking.core.responses.client.ClientRemoveResponse;
import lv.javaguru.java2.eBooking.core.responses.client.ClientSearchResponse;
import lv.javaguru.java2.eBooking.core.services.client.ClientAddService;
import lv.javaguru.java2.eBooking.core.services.client.ClientRemoveService;
import lv.javaguru.java2.eBooking.core.services.client.ClientSearchService;
import org.junit.Test;

import static org.junit.Assert.*;

public class Acceptance_Test3 {

    private ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void shouldRemoveCorrectClient(){
        ClientAddRequest request1 = new ClientAddRequest(
                "alina@gmail.com",
                "0037126146341");
        getClientAddService().execute(request1);

        ClientAddRequest request2 = new ClientAddRequest(
                "yevgeniy@gmail.com",
                "0037126395758");
        getClientAddService().execute(request2);

        ClientRemoveRequest request3 = new ClientRemoveRequest(1L);
        ClientRemoveResponse response = getClientRemoveService().execute(request3);

        ClientSearchRequest request4 = new ClientSearchRequest("yevgeniy@gmail.com",null);
        ClientSearchResponse response1 = getClientSearchService().execute(request4);

        assertTrue(response.isClientRemoved());
        assertEquals(response1.getClients().size(),1);
        assertEquals(response1.getClients().get(0).getClientEmail(),"yevgeniy@gmail.com");
        assertEquals(response1.getClients().get(0).getClientPhoneNumber(),"0037126395758");
    }

    public ClientSearchService getClientSearchService() {
        return applicationContext.getBean(ClientSearchService.class);
    }

    public ClientRemoveService getClientRemoveService() {
        return applicationContext.getBean(ClientRemoveService.class);
    }

    public ClientAddService getClientAddService() {
        return applicationContext.getBean(ClientAddService.class);
    }
}
