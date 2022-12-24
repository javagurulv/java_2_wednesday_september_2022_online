package myApp.core.services;

import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.database.jpa.JpaUserRepository;
import myApp.core.domain.User;
import myApp.core.requests.AddUserRequest;
import myApp.core.responses.AddUserResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.AddUserValidator;
import myApp.web_ui.security.EncoderAndDecoderPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class AddUserService {

    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private EncoderAndDecoderPassword encoder;
    @Autowired
    private AddUserValidator validator;

    public AddUserResponse execute(AddUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            User user = new User(encoder.executeEncode(request.getPersonalCode()), encoder.executeEncode("{noop}" +request.getPassword()),
                    encoder.executeEncode("Role_User"));
            userRepository.save(user);
            return new AddUserResponse(user);
        }
        return new AddUserResponse(errors);
    }
}
