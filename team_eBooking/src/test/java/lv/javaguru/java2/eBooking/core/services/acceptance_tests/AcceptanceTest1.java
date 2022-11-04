package lv.javaguru.java2.eBooking.core.services.acceptance_tests;

import lv.javaguru.java2.eBooking.ApplicationContext;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientAddRequest;
import lv.javaguru.java2.eBooking.core.requests.client_request.ClientGetAllRequest;
import lv.javaguru.java2.eBooking.core.responses.client.ClientsGetAllResponse;
import lv.javaguru.java2.eBooking.core.services.client.ClientAddService;
import lv.javaguru.java2.eBooking.core.services.client.ClientGetAllService;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AcceptanceTest1 {

    private ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void shouldReturnClientList(){
        ClientAddRequest request1 = new ClientAddRequest(
                "email@gmail.com",
                "0037111111111");
        getClientAddService().execute(request1);

        ClientAddRequest request2 = new ClientAddRequest(
                "email1@gmail.com",
                "0037122222222");
        getClientAddService().execute(request2);

        ClientGetAllRequest request = new ClientGetAllRequest();
        ClientsGetAllResponse response = getAllClientService().execute(request);
        assertEquals(response.getClients().size(),2);
    }
    public ClientAddService getClientAddService() {
        return applicationContext.getBean(ClientAddService.class);
    }

    public ClientGetAllService getAllClientService() {
        return applicationContext.getBean(ClientGetAllService.class);
    }
}
