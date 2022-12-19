package lv.javaguru.java2.rentapp.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Client {
    private Long id;
    private String personalId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Client() {
    }

    public Client(String personalId, String firstName, String lastName, String email, String phoneNumber) {
        this.personalId = personalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}

