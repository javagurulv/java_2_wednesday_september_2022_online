package lv.javaguru.java2.rentapp.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return getPersonalId().equals(client.getPersonalId()) && getFirstName().equals(client.getFirstName()) &&
                getLastName().equals(client.getLastName()) && getEmail().equals(client.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonalId(), getFirstName(), getLastName(), getEmail());
    }
}

