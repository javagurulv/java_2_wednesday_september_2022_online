package myApp.core.services;

import myApp.core.database.jpa.JpaUserRepository;
import myApp.core.domain.User;
import myApp.core.requests.GetAllUsersRequest;
import myApp.core.responses.GetAllUsersResponse;
import myApp.web_ui.security.EncoderAndDecoderPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetAllUsersService {
    @Autowired
    private JpaUserRepository userRepository;
    /*
    @Autowired
    private EncoderAndDecoderPassword decoder;

     */

    public GetAllUsersResponse execute(GetAllUsersRequest request) {
        List<User> users = userRepository.findAll();
/*
        for (User user : users) {
            user.setPersonalCode(decoder.executeDecode(user.getPersonalCode()));
            user.setPassword(decoder.executeDecode(user.getPassword()));
            user.setRole(decoder.executeDecode(user.getRole()));
        }

 */
        return new GetAllUsersResponse(null, users);
    }
}
