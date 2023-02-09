package myApp.core.services.validators;

import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.RepayTheLoanRequest;
import myApp.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RepayTheLoanValidator {


    public List<CoreError> execute(RepayTheLoanRequest request) {
        List<CoreError> errors = new ArrayList<>();
        isValueOfMoneyNotNull(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> isValueOfMoneyNotNull(RepayTheLoanRequest request) {
        return request.getValue() != null
                ? Optional.empty()
                : Optional.of(new CoreError("Value", "Must not be empty"));
    }

}