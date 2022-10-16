package lv.javaguru.java2.rentapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Client {

    String personalId;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;

}

