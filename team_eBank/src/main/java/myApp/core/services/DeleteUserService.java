package myApp.core.services;

import liquibase.pro.packaged.A;
import myApp.core.database.jpa.JpaUserRepository;
import myApp.core.requests.DeleteUserRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.DeleteUserResponses;
import myApp.core.services.validators.DeleteUserValidator;
import myApp.web_ui.security.EncoderAndDecoderPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class DeleteUserService {

    @Autowired
    private JpaUserRepository jpaUserRepository;
    @Autowired
    private EncoderAndDecoderPassword encoder;
    @Autowired
    private DeleteUserValidator validator;

    public DeleteUserResponses execute(DeleteUserRequest request) {
        List<CoreError> errors = validator.execute(request);
        if (errors.isEmpty()) {
            String encodedPersonalCode = encoder.executeEncode(request.getPersonalCode());
            jpaUserRepository.deleteByPersonalCode(encodedPersonalCode);
            return new DeleteUserResponses(true);
        } else {
            return new DeleteUserResponses(errors);
        }
    }
}
