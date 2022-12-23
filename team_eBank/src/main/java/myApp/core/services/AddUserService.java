package myApp.core.services;

import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.database.jpa.JpaUserRepository;
import myApp.core.domain.User;
import myApp.core.requests.AddUserRequest;
import myApp.core.responses.AddUserResponse;
import myApp.web_ui.security.EncoderAndDecoderPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class AddUserService {

    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private EncoderAndDecoderPassword encoder;

    public AddUserResponse execute(AddUserRequest request) {
        User user = new User(encoder.executeEncode(request.getPersonalCode()), encoder.executeEncode("{noop}" +request.getPassword()),
                encoder.executeEncode("Role_User"));
        userRepository.save(user);
        return new AddUserResponse(user);
    }
}
