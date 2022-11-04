package lv.javaguru.java2.eBooking.core.services.acceptance_tests;

import lv.javaguru.java2.eBooking.ApplicationContext;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientAddRequest;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientSearchRequest;
import lv.javaguru.java2.eBooking.core.responses.client.ClientSearchResponse;
import lv.javaguru.java2.eBooking.core.services.client.ClientAddService;
import lv.javaguru.java2.eBooking.core.services.client.ClientSearchService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Acceptance_Test2 {

    private ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void shouldSearchForCorrectClient(){
        ClientAddRequest request1 = new ClientAddRequest(
                "alina@gmail.com",
                "0037126146341");
        getClientAddService().execute(request1);
        ClientAddRequest request2 = new ClientAddRequest(
                "yevgeniy@gmail.com",
                "0037126395758");
        getClientAddService().execute(request2);

        ClientSearchRequest request3 = new ClientSearchRequest(
                "alina@gmail.com",
                null  );
        ClientSearchResponse response = getClientSearchService().execute(request3);

        assertEquals(response.getClients().size(),1);
        assertEquals(response.getClients().get(0).getClientEmail(),"alina@gmail.com");
        assertEquals(response.getClients().get(0).getClientPhoneNumber(),"0037126146341");
    }

    public ClientSearchService getClientSearchService(){
        return applicationContext.getBean(ClientSearchService.class);
    }

    public ClientAddService getClientAddService(){
        return applicationContext.getBean(ClientAddService.class);
    }

}
