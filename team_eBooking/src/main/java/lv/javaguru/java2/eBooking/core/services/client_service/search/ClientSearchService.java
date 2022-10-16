package lv.javaguru.java2.eBooking.core.services.client_service.search;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.requests.client_request.SearchClientRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.client_response.SearchClientResponse;

import java.util.List;

public class ClientSearchService {

    private Database database;
    private ClientSearchRequestValidator validator;

    public ClientSearchService(Database database, ClientSearchRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }
    public SearchClientResponse execute(SearchClientRequest request){
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()){
            return new SearchClientResponse(errors, null);
        }
        List<Client> client = null;
        if(request.isEmailProvided() && !request.isPhoneNumberProvided()){
            client = database.findClientByEMail(request.getClientEmail());
        }
        if(request.isPhoneNumberProvided() && !request.isEmailProvided()){
            client=database.findClientByPhoneNumber(request.getClientPhoneNumber());
        }
        if(request.isEmailProvided() && request.isPhoneNumberProvided()){
            client=database.findClientByEmailAndPhoneNumber(request.getClientEmail(), request.getClientPhoneNumber());
        }
        System.out.println("Client not found");
        return new SearchClientResponse(null, client);
    }



}
