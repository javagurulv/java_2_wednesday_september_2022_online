package lv.javaguru.java2.eBooking.core.services.matchers;

import lv.javaguru.java2.eBooking.core.domain.Client;
import org.mockito.ArgumentMatcher;

public class ClientMatcher implements ArgumentMatcher<Client> {

    private String clientEmail;
    private String clientPhoneNumber;

    public ClientMatcher(String clientEmail, String clientPhoneNumber) {
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;
    }
    @Override
    public boolean matches(Client argument) {
        return argument.getClientEmail().equals(clientEmail)
                && argument.getClientPhoneNumber().equals(clientPhoneNumber);
    }
}
