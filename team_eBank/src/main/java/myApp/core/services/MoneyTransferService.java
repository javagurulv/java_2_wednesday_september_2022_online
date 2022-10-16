package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.MoneyTransferRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.MoneyTransferResponse;
import myApp.core.services.validators.MoneyTransferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MoneyTransferService {
    @Autowired
    private DataBase dataBase;
    @Autowired
    private MoneyTransferValidator validator;

    public MoneyTransferResponse execute(MoneyTransferRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            boolean result = dataBase.bankTransfer(request.getPersonalCode(),request.getAnotherPersonalCode(),
                    request.getValue());
            return new MoneyTransferResponse(result);
        } else {
            return new MoneyTransferResponse(errors);
        }
    }
}
